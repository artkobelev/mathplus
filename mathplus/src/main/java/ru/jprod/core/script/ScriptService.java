package ru.jprod.core.script;

import java.util.Map;

/**
 * Интерфейс для работы со скриптами
 *
 * @author artem
 * @since 27.05.19
 */
public interface ScriptService
{
    /**
     * Выполнить скрипт
     *
     * @param script тело скрипта
     * @return результат выполнения
     */
    Object execute(String script);

    /**
     * Выполнить скрипт
     *
     * @param script  тело скрипта
     * @param context значения контекста
     * @return результат выполнения
     */
    Object execute(String script, Map<String, Object> context);
}
