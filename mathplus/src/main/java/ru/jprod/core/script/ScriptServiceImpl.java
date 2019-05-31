package ru.jprod.core.script;

import java.util.Collections;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.script.Bindings;
import javax.script.Compilable;
import javax.script.CompiledScript;
import javax.script.ScriptContext;
import javax.script.ScriptEngine;
import javax.script.ScriptException;
import javax.script.SimpleBindings;
import javax.script.SimpleScriptContext;

import org.codehaus.groovy.jsr223.GroovyScriptEngineFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.google.common.collect.Maps;

import ru.jprod.core.script.api.ScriptApi;
import ru.jprod.util.Constants;
import ru.jprod.util.context.SpringUtils;
import ru.jprod.util.exceptions.MathplusException;

/**
 * Сервис для работы со скриптами
 *
 * @author artem
 * @since 27.05.19
 */
@Component
public class ScriptServiceImpl implements ScriptService
{
    private static final Logger LOGGER = LoggerFactory.getLogger(ScriptServiceImpl.class);

    private Map<String, Object> api;
    private ScriptEngine engine = new GroovyScriptEngineFactory().getScriptEngine();

    @Override
    public Object execute(String script, Map<String, Object> context)
    {
        CompiledScript compiled = compile(script);
        ScriptContext scriptContext = createScriptContext(createBindings(context));

        try
        {
            return compiled.eval(scriptContext);
        }
        catch (ScriptException ex)
        {
            throw new MathplusException("script execution failed", ex);
        }
    }

    @Override
    public Object execute(String script)
    {
        return execute(script, Maps.newHashMap());
    }

    /**
     * Скомпилировать скрипт
     *
     * @param script скрипт
     * @return скомпилированный скрипт
     */
    private CompiledScript compile(String script)
    {
        try
        {
            return ((Compilable)engine).compile(script);
        }
        catch (ScriptException ex)
        {
            throw new MathplusException("script compilation failed", ex);
        }
    }

    /**
     * Создать бинды скрипта
     *
     * @param context значения контекста
     * @return
     */
    private Bindings createBindings(Map<String, Object> context)
    {
        Bindings bindings = new SimpleBindings();
        bindings.putAll(context);
        bindings.put(Constants.ScriptService.API, api);
        bindings.put(Constants.ScriptService.LOG, LOGGER);
        return bindings;
    }

    /**
     * Создать контекст скрипта
     *
     * @param bindings бинды
     * @return
     */
    private ScriptContext createScriptContext(Bindings bindings)
    {
        ScriptContext scriptContext = new SimpleScriptContext();
        scriptContext.setBindings(bindings, ScriptContext.ENGINE_SCOPE);
        return scriptContext;
    }

    @PostConstruct
    private void initApi()
    {
        Map<String, Object> apis = Maps.newHashMap();
        SpringUtils.get().getBeans(ScriptApi.class).forEach((key, val) -> apis.put(val.code(), val));
        api = Collections.unmodifiableMap(apis);
    }
}
