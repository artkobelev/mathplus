package ru.jprod.rest;

/**
 * Константы для работы с REST API для работы со скриптами
 *
 * @author artem
 * @since 25.05.19
 */
public class ExecConstants
{
    public static final String ROOT_URI = "/rest/exec";
    public static final String EXEC_ALGORITHM_URI = ROOT_URI + "/algth/{" + AlgorithmConstants.PATH_NAME + "}";
}
