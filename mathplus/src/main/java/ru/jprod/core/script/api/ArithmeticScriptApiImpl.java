package ru.jprod.core.script.api;

import java.util.Collection;

import org.springframework.stereotype.Component;

import ru.jprod.math.ArithmeticService;

/**
 * API для выполнения арифметических операций
 *
 * @author artem
 * @since 30.05.19
 */
@Component
public class ArithmeticScriptApiImpl implements ArithmeticScriptApi
{
    private static final String CODE = "arithm";

    private final ArithmeticService arithmService;

    public ArithmeticScriptApiImpl(ArithmeticService arithmService)
    {
        this.arithmService = arithmService;
    }

    @Override
    public double add(double number1, double number2)
    {
        return arithmService.add(number1, number2);
    }

    @Override
    public double average(Collection<Number> numbers)
    {
        return arithmService.average(numbers);
    }

    @Override
    public String code()
    {
        return CODE;
    }

    @Override
    public long dec(long number)
    {
        return arithmService.dec(number);
    }

    @Override
    public double div(double number1, double number2)
    {
        return arithmService.div(number1, number2);
    }

    @Override
    public long inc(long number)
    {
        return arithmService.inc(number);
    }

    @Override
    public double mul(double number1, double number2)
    {
        return arithmService.mul(number1, number2);
    }

    @Override
    public double neg(double number)
    {
        return arithmService.neg(number);
    }

    @Override
    public double sub(double number1, double number2)
    {
        return arithmService.sub(number1, number2);
    }

    @Override
    public double sum(Collection<Number> numbers)
    {
        return arithmService.sum(numbers);
    }
}
