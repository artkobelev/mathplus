package ru.jprod.math;

import java.util.Collection;

/**
 * Выполнение арифметических операций
 *
 * @author artem
 * @since 21.05.2019
 */
public interface ArithmeticService
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
     * Найти среднее значение чисел
     *
     * @param numbers числа
     * @return среднее значение
     */
    Double average(Collection<Double> numbers);

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

    /**
     * Найти сумму чисел
     *
     * @param numbers числа
     * @return сумма чисел
     */
    Double sum(Collection<Double> numbers);
}