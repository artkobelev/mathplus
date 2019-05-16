package ru.jprod.core.model.math;

import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class MathServiceImpl implements MathService
{
    private static final Logger LOGGER = LoggerFactory.getLogger(MathServiceImpl.class);

    @Override public Double sum(Collection<Double> numbers)
    {
        LOGGER.debug("calculating sum of: " + numbers);
        return numbers.stream().mapToDouble(v -> v).sum();
    }

    @Override public Double sub(Double n, Collection<Double> numbers)
    {
        return n - sum(numbers);
    }

    @Override public Double neg(Double n)
    {
        return -n;
    }
}
