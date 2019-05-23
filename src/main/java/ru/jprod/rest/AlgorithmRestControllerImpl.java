package ru.jprod.rest;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.base.Preconditions;

import ru.jprod.core.model.Algorithm;
import ru.jprod.core.model.algorithm.dao.DAOAlgorithm;
import ru.jprod.core.model.dto.DtoMapper;
import ru.jprod.core.model.dto.MapperService;
import ru.jprod.util.TxRunner;
import ru.jprod.util.ValidationUtils;

/**
 * Методы REST контроллера для работы с алгоритмом {@link Algorithm}
 *
 * @author artem
 * @since 21.05.2019
 */
@RestController
@RequestMapping("/rest/algth")
public class AlgorithmRestControllerImpl implements AlgorithmRestController
{
    @Inject
    private DAOAlgorithm daoAlgorithm;

    @Inject
    private MapperService mapper;

    @Inject
    private ValidationUtils validationUtils;

    @Override
    @RequestMapping(value = "/{algthName}", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public long create(@PathVariable("algthName") String name, @RequestBody Map<String, Object> data)
    {
        Preconditions.checkNotNull(data);
        return TxRunner.call(() -> {
            daoAlgorithm.checkAbsent(name);
            data.put(DtoMapper.TYPE, Algorithm.class.getName());
            Algorithm algorithm = mapper.restore(data);
            algorithm.setName(name);
            validationUtils.validate(algorithm);
            return daoAlgorithm.save(algorithm);
        });
    }

    @Override
    @RequestMapping(value = "/{algthName}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable("algthName") String name)
    {
        TxRunner.run(() -> daoAlgorithm.delete(daoAlgorithm.getExisting(name)));
    }

    @Override
    @RequestMapping(method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public List<String> getAll()
    {
        return TxRunner.call(() -> {
            return daoAlgorithm.getAllNames();
        });
    }

    @Override
    @RequestMapping(value = "/{algthName}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public Map<String, Object> getByName(@PathVariable("algthName") String name)
    {
        return TxRunner.call(() -> {
            return mapper.packToMap(daoAlgorithm.getExisting(name));
        });
    }

    @Override
    @RequestMapping(value = "/{algthName}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public void update(@PathVariable("algthName") String name, @RequestBody Map<String, Object> data)
    {
        Preconditions.checkNotNull(data);
        TxRunner.run(() -> {
            Algorithm algorithm = mapper.update(data, daoAlgorithm.getExisting(name));
            validationUtils.validate(algorithm);
            daoAlgorithm.update(algorithm);
        });
    }
}
