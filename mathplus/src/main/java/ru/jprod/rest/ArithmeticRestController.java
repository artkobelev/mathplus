package ru.jprod.rest;

import java.util.Collection;

/**
 * Методы REST контроллера для выполнения арифметических операций
 *
 * @author artem
 * @since 21.05.2019
 */
public interface ArithmeticRestController
{
    /**
     * Найти сумму двух чисел
     *
     * @param number1 первое число
     * @param number2 второе число
     * @return сумма двух чисел
     */
    double add(double number1, double number2);

    /**
     * Найти среднее значение чисел
     *
     * @param numbers числа
     * @return среднее значение
     */
    double average(Collection<Number> numbers);

    /**
     * Уменьшить значение числа на единицу
     *
     * @param number число
     * @return число минус 1
     */
    long dec(long number);

    /**
     * Найти частное двух чисел
     *
     * @param number1 первое число
     * @param number2 второе число
     * @return частное двух чисел
     */
    double div(double number1, double number2);

    /**
     * Увеличить значение числа на единицу
     *
     * @param number число
     * @return число плюс 1
     */
    long inc(long number);

    /**
     * Найти произведение двух чисел
     *
     * @param number1 первое число
     * @param number2 второе число
     * @return произведение двух чисел
     */
    double mul(double number1, double number2);

    /**
     * Найти разность двух чисел
     *
     * @param number1 первое число
     * @param number2 второе число
     * @return разность двух чисел
     */
    double sub(double number1, double number2);

    /**
     * Найти сумму чисел
     *
     * @param numbers числа
     * @return сумма чисел
     */
    double sum(Collection<Number> numbers);
}
