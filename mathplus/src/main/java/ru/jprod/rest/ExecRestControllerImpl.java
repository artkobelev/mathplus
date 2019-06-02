package ru.jprod.rest;

import java.util.Map;

import javax.inject.Inject;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.base.Preconditions;

import ru.jprod.core.model.Algorithm;
import ru.jprod.core.model.algorithm.dao.DAOAlgorithm;
import ru.jprod.core.script.ScriptService;
import ru.jprod.util.JsonUtils;
import ru.jprod.util.TxRunner;

/**
 * REST контроллер для работы со скриптами
 *
 * @author artem
 * @since 27.05.19
 */
@RestController
@RequestMapping("/rest/exec")
public class ExecRestControllerImpl implements ExecRestController
{
    @Inject
    private DAOAlgorithm daoAlgorithm;

    @Inject
    private JsonUtils jsonUtils;

    @Inject
    private ScriptService scriptService;

    @Override
    @RequestMapping(method = RequestMethod.POST, produces = "application/json")
    public String execute(@RequestBody String script)
    {
        return jsonUtils.toJson(scriptService.execute(script));
    }

    @Override
    @RequestMapping(value = "/algth/{algthName}", method = RequestMethod.POST, produces = "application/json")
    public String executeAlgorithm(@PathVariable("algthName") String name, @RequestBody Map<String, Object> context)
    {
        Preconditions.checkNotNull(context);
        return TxRunner.call(() -> {
            Algorithm algorithm = daoAlgorithm.getExisting(name);
            return jsonUtils.toJson(scriptService.execute(algorithm.getScript(), context));
        });
    }
}
