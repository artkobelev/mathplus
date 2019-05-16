package ru.jprod.rest;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import ru.jprod.config.ConfigProperties;
import ru.jprod.config.DBConfigProperties;
import ru.jprod.core.model.math.MathService;
import ru.jprod.core.model.math.MathServiceImpl;

@RestController
@RequestMapping("/rest/math")
public class MathControllerImpl implements MathController
{
    @Inject
    private ConfigProperties configProperties;

    @Inject
    private DBConfigProperties dbConfigProperties;

    @Inject
    private MathService mathService;

    private static final Logger LOGGER = LoggerFactory.getLogger(MathServiceImpl.class);

    @RequestMapping(value = "avg", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @Override public Double average(@RequestParam(name = "numbers") List<Double> numbers)
    {
        return numbers.stream().mapToDouble(val -> val).average().orElse(0.0);
    }

    @RequestMapping(value = "dec/{number}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @Override public Long dec(@PathVariable(name = "number") Long number)
    {
        return --number;
    }

    @RequestMapping(value = "/inc/{number}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @Override public Long inc(@PathVariable(name = "number") Long number)
    {
        return ++number;
    }

    @RequestMapping(value = "/sum", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @Override public Double sum(@RequestParam(name = "numbers") List<Double> numbers)
    {
        LOGGER.debug(configProperties.getProp2());
        LOGGER.debug(dbConfigProperties.getProp1());
        return mathService.sum(numbers);
    }
}
