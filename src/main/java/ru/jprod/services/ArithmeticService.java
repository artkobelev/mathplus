package ru.jprod.services;

import java.util.Collection;
import java.util.List;

public interface ArithmeticService
{
    Double add(Double number1, Double number2);

    Double average(List<Double> numbers);

    Long dec(Long number);

    Double div(Double number1, Double number2);

    Long inc(Long number);

    Double mul(Double number1, Double number2);

    Double neg(Double n);

    Double sub(Double number1, Double number2);

    Double sum(Collection<Double> numbers);
}
