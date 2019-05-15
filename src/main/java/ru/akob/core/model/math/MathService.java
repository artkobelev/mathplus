package ru.akob.core.model.math;

import java.util.Collection;

public interface MathService
{
    Double sum(Collection<Double> numbers);

    Double sub(Double n, Collection<Double> numbers);

    Double neg(Double n);
}
