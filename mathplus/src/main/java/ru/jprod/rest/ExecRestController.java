package ru.jprod.rest;

/**
 * REST контроллер для выполнения скриптов
 *
 * @author artem
 * @since 27.05.19
 */
public interface ExecRestController
{
    /**
     * Выполнить скрипт
     *
     * @param script скрипт
     * @return результат выполнения
     */
    String execute(String script);
}
