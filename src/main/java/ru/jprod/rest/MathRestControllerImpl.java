package ru.jprod.rest;

import java.util.Collection;

import javax.inject.Inject;

import org.apache.commons.math3.util.Precision;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import ru.jprod.core.config.MathProperties;
import ru.jprod.math.ArithmeticService;
import ru.jprod.math.ArithmeticServiceImpl;

/**
 * Методы REST контроллера для выполнения арифметических операций
 *
 * @author artem
 * @since 21.05.2019
 */
@RestController
@RequestMapping("/rest/math")
public class MathRestControllerImpl implements MathRestController
{
    private static final Logger LOGGER = LoggerFactory.getLogger(ArithmeticServiceImpl.class);

    @Inject
    private MathProperties mathProperties;
    @Inject
    private ArithmeticService mathService;

    @RequestMapping(value = "add", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @Override
    public Double add(Double number1, Double number2)
    {
        LOGGER.debug("adding {} and {} with scale {}", number1, number2, mathProperties.getRounding());
        return Precision.round(mathService.add(number1, number2), mathProperties.getRounding());
    }

    @RequestMapping(value = "avg", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @Override
    public Double average(@RequestParam(name = "numbers") Collection<Double> numbers)
    {
        LOGGER.debug("average of {} with scale {}", numbers, mathProperties.getRounding());
        return Precision.round(mathService.average(numbers), mathProperties.getRounding());
    }

    @RequestMapping(value = "dec/{number}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @Override
    public Long dec(@PathVariable(name = "number") Long number)
    {
        LOGGER.debug("dec {}", number);
        return mathService.dec(number);
    }

    @RequestMapping(value = "/div", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @Override
    public Double div(Double number1, Double number2)
    {
        LOGGER.debug("dividing {} on {} with scale {}", number1, number2, mathProperties.getRounding());
        return Precision.round(mathService.div(number1, number2), mathProperties.getRounding());
    }

    @RequestMapping(value = "/inc/{number}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @Override
    public Long inc(@PathVariable(name = "number") Long number)
    {
        LOGGER.debug("inc {}", number);
        return mathService.inc(number);
    }

    @RequestMapping(value = "mul", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @Override
    public Double mul(Double number1, Double number2)
    {
        LOGGER.debug("multiply {} and {} with scale {}", number1, number2, mathProperties.getRounding());
        return Precision.round(mathService.mul(number1, number2), mathProperties.getRounding());
    }

    @RequestMapping(value = "sub", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @Override
    public Double sub(Double number1, Double number2)
    {
        LOGGER.debug("subbing {} and {} with scale {}", number1, number2, mathProperties.getRounding());
        return Precision.round(mathService.sub(number1, number2), mathProperties.getRounding());
    }

    @RequestMapping(value = "/sum", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @Override
    public Double sum(@RequestParam(name = "numbers") Collection<Double> numbers)
    {
        LOGGER.debug("sum of {} with scale {}", numbers, mathProperties.getRounding());
        return Precision.round(mathService.sum(numbers), mathProperties.getRounding());
    }
}
