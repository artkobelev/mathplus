package ru.jprod.core.script.api;

/**
 * API для выполнения арифметических операций
 *
 * @author artem
 * @since 30.05.19
 */
public interface ArithmeticScriptApi extends ScriptApi
{
    /**
     * Найти сумму двух чисел
     *
     * @param number1 первое число
     * @param number2 второе число
     * @return сумма двух чисел
     */
    Double add(Double number1, Double number2);

    /**
     * Уменьшить значение числа на единицу
     *
     * @param number число
     * @return число минус 1
     */
    Long dec(Long number);

    /**
     * Найти частное двух чисел
     *
     * @param number1 первое число
     * @param number2 второе число
     * @return частное двух чисел
     */
    Double div(Double number1, Double number2);

    /**
     * Увеличить значение числа на единицу
     *
     * @param number число
     * @return число плюс 1
     */
    Long inc(Long number);

    /**
     * Найти произведение двух чисел
     *
     * @param number1 первое число
     * @param number2 второе число
     * @return произведение двух чисел
     */
    Double mul(Double number1, Double number2);

    /**
     * Изменить знак числа
     *
     * @param number число
     * @return число с противоположным знаком
     */
    Double neg(Double number);

    /**
     * Найти разность двух чисел
     *
     * @param number1 первое число
     * @param number2 второе число
     * @return разность двух чисел
     */
    Double sub(Double number1, Double number2);
}
