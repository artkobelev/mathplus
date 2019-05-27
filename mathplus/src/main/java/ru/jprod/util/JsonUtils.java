package ru.jprod.util;

import java.io.IOException;

import javax.inject.Inject;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import ru.jprod.util.exceptions.MathplusException;

/**
 * Утилиты работы с JSON
 *
 * @author artem
 * @since 27.05.2019
 */
@Component
public class JsonUtils
{
    @Inject
    private ObjectMapper objectMapper;

    /**
     * Распарсить значение из JSON
     *
     * @param raw  JSON
     * @param type тип получаемого значения
     * @return значение
     */
    public <T> T readValue(String raw, Class<T> type)
    {
        try
        {
            return objectMapper.readValue(raw, type);
        }
        catch (IOException e)
        {
            throw new MathplusException(e.getMessage(), e);
        }
    }

    /**
     * Упаковать объект в JSON
     *
     * @param value объект
     * @return JSON представление
     */
    public String toJson(Object value)
    {
        try
        {
            return objectMapper.writeValueAsString(value);
        }
        catch (JsonProcessingException e)
        {
            throw new MathplusException(e.getMessage(), e);
        }
    }
}
