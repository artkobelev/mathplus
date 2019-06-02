package ru.jprod.math;

import java.util.Collection;

import org.springframework.stereotype.Service;

/**
 * Выполнение арифметических операций
 *
 * @author artem
 * @since 21.05.2019
 */
@Service
public class ArithmeticServiceImpl implements ArithmeticService
{
    @Override
    public double add(double number1, double number2)
    {
        return number1 + number2;
    }

    @Override
    public double average(Collection<Number> numbers)
    {
        return numbers.stream().mapToDouble(val -> val.doubleValue()).average().orElse(0.0);
    }

    @Override
    public long dec(long number)
    {
        return number - 1;
    }

    @Override
    public double div(double number1, double number2)
    {
        return number1 / number2;
    }

    @Override
    public long inc(long number)
    {
        return number + 1;
    }

    @Override
    public double mul(double number1, double number2)
    {
        return number1 * number2;
    }

    @Override
    public double neg(double number)
    {
        return -number;
    }

    @Override
    public double sub(double number1, double number2)
    {
        return number1 - number2;
    }

    @Override
    public double sum(Collection<Number> numbers)
    {
        return numbers.stream().mapToDouble(v -> v.doubleValue()).sum();
    }
}
