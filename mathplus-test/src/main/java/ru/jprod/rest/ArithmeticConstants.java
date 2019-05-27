package ru.jprod.rest;

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
    public static final String ADD_URI = ROOT_URI + "/add";
    public static final String DIV_URI = ROOT_URI + "/div";
    public static final String MUL_URI = ROOT_URI + "/mul";
    public static final String AVG_URI = ROOT_URI + "/avg";
    public static final String SUB_URI = ROOT_URI + "/sub";
    public static final String SUM_URI = ROOT_URI + "/sum";
}
