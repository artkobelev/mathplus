package ru.jprod.rest;

import java.util.List;

public interface MathRestController
{
    Double add(Double number1, Double number2);

    Double average(List<Double> numbers);

    Long dec(Long number);

    Double div(Double number1, Double number2);

    Long inc(Long number);

    Double mul(Double number1, Double number2);

    Double sub(Double number1, Double number2);

    Double sum(List<Double> numbers);
}
