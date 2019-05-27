package ru.jprod.core.script;

import org.springframework.stereotype.Component;

/**
 * Сервис для работы со скриптами
 *
 * @author artem
 * @since 27.05.19
 */
@Component
public class ScriptServiceImpl implements ScriptService
{
    @Override
    public Object execute(String script)
    {
        return script;
    }
}
