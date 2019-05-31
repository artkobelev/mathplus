package ru.jprod.rest;

import javax.inject.Inject;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ru.jprod.core.script.ScriptService;
import ru.jprod.util.JsonUtils;

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
    private JsonUtils jsonUtils;

    @Inject
    private ScriptService scriptService;

    @Override
    @RequestMapping(method = RequestMethod.POST, produces = "application/json")
    public String execute(@RequestBody String script)
    {
        return jsonUtils.toJson(scriptService.execute(script));
    }
}
