package ru.jprod.rest;

import java.util.Map;

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

    /**
     * Выполнить алгоритм
     *
     * @param name    имя алгоритма
     * @param context контекст
     * @return результат выполнения
     */
    String executeAlgorithm(String name, Map<String, Object> context);
}
