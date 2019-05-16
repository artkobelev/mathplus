package ru.jprod.rest;

import java.util.List;

public interface MathController
{
    Double average(List<Double> numbers);

    Long dec(Long number);

    Long inc(Long number);

    Double sum(List<Double> numbers);
}
