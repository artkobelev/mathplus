package ru.jprod.core.config;

import javax.validation.constraints.Min;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

/**
 * Конфигурация приложения для математических вычислений
 *
 * @author artem
 * @since 23.05.2019
 */
@ConfigurationProperties(prefix = "math")
@Validated
public class MathProperties
{
    /**
     * Погрешность вычисления
     */
    @Min(0)
    private double delta;

    /**
     * Количество знаков округление
     */
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