package ru.jprod.core.script;

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
}
