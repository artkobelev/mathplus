package ru.jprod;

/**
 * Константы для работы с REST API для выполнения арифметических операций
 *
 * @author artem
 * @since 25.05.19
 */
public class ArithmeticConstants
{
    public static final String PATH_NUMBER = "number";
    public static final String ROOT_URI = "/rest/arithm";
    public static final String INC_URI = ROOT_URI + "/inc/{" + PATH_NUMBER + "}";
    public static final String DEC_URI = ROOT_URI + "/dec/{" + PATH_NUMBER + "}";
}
