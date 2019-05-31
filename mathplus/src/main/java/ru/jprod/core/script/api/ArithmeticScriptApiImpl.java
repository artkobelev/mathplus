package ru.jprod.core.script.api;

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
    public Double add(Double number1, Double number2)
    {
        return arithmService.add(number1, number2);
    }

    @Override
    public String code()
    {
        return CODE;
    }

    @Override
    public Long dec(Long number)
    {
        return arithmService.dec(number);
    }

    @Override
    public Double div(Double number1, Double number2)
    {
        return arithmService.div(number1, number2);
    }

    @Override
    public Long inc(Long number)
    {
        return arithmService.inc(number);
    }

    @Override
    public Double mul(Double number1, Double number2)
    {
        return arithmService.mul(number1, number2);
    }

    @Override
    public Double neg(Double number)
    {
        return arithmService.neg(number);
    }

    @Override
    public Double sub(Double number1, Double number2)
    {
        return arithmService.sub(number1, number2);
    }
}
