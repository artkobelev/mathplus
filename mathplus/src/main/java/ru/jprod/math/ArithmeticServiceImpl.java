package ru.jprod.math;

import java.util.List;

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
    public Double add(Double number1, Double number2)
    {
        return number1 + number2;
    }

    @Override
    public Double average(List<Double> numbers)
    {
        return numbers.stream().mapToDouble(val -> val).average().orElse(0.0);
    }

    @Override
    public Long dec(Long number)
    {
        return --number;
    }

    @Override
    public Double div(Double number1, Double number2)
    {
        return number1 / number2;
    }

    @Override
    public Long inc(Long number)
    {
        return ++number;
    }

    @Override
    public Double mul(Double number1, Double number2)
    {
        return number1 * number2;
    }

    @Override
    public Double neg(Double number)
    {
        return -number;
    }

    @Override
    public Double sub(Double number1, Double number2)
    {
        return number1 - number2;
    }

    @Override
    public Double sum(List<Double> numbers)
    {
        return numbers.stream().mapToDouble(v -> v).sum();
    }
}
