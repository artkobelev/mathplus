package ru.jprod.rest;

/**
 * Константы для работы с REST API для работы с алгоритмом
 *
 * @author artem
 * @since 25.05.19
 */
public class AlgorithmConstants
{
    public static final String OBJECT_NOT_FOUND_MESSAGE = "Object with type=ru.jprod.core.model.Algorithm and name=%s not found";
    public static final String PATH_NAME = "algthName";
    public static final String ROOT_URI = "/rest/algth";
    public static final String ALGORITHM_URI = ROOT_URI + "/{" + PATH_NAME + "}";
}
