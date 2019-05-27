package ru.jprod.rest;

import javax.inject.Inject;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<String> execute(@RequestBody String script)
    {
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.setContentType(MediaType.APPLICATION_JSON_UTF8);
        responseHeaders.add("Access-Control-Allow-Origin", "*");
        return new ResponseEntity<>(jsonUtils.toJson(scriptService.execute(script)), responseHeaders, HttpStatus.OK);
    }
}
