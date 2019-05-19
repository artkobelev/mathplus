package ru.jprod.core.config;

import javax.validation.constraints.Min;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

@ConfigurationProperties(prefix = "math")
@Validated
public class MathProperties
{
    @Min(0)
    private double delta;

    @Min(0)
    private int rounding;

    public double getDelta()
    {
        return delta;
    }

    public int getRounding()
    {
        return rounding;
    }

    public void setDelta(double delta)
    {
        this.delta = delta;
    }

    public void setRounding(int rounding)
    {
        this.rounding = rounding;
    }
}