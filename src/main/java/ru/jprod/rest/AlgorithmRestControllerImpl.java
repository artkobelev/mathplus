package ru.jprod.rest;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import ru.jprod.util.TxRunner;

@RestController
@RequestMapping("/rest/algth")
public class AlgorithmRestControllerImpl implements AlgorithmRestController
{
    private static final Logger LOGGER = LoggerFactory.getLogger(AlgorithmRestControllerImpl.class);

    @Inject
    private DAOAlgorithm daoAlgorithm;

    @Inject
    private SessionFactory sessionFactory;

    @Override
    @RequestMapping(value = "/{algthName}", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public long create(@PathVariable("algthName") String name, @RequestBody Map<String, Object> data)
    {
        Preconditions.checkNotNull(data);

        return TxRunner.call(() -> {
            daoAlgorithm.checkAbsent(name);
            //            Endpoint endpoint = mapper.restore(data);
            //            endpoint.setName(name);
            //            validUtils.validate(endpoint);
            Algorithm alg = new Algorithm();
            alg.setName(name);
            return daoAlgorithm.save(alg);
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
    public List<String> getAll()
    {
        return TxRunner.call(() -> {
            return daoAlgorithm.getAllNames();
        });
    }

    @Override
    public void update(String name, Map<String, Object> data)
    {

    }
}
