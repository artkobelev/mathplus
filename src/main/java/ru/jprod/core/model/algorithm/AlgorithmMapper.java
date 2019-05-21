package ru.jprod.core.model.algorithm;

import java.util.Map;

import org.springframework.stereotype.Component;

import ru.jprod.core.model.Algorithm;
import ru.jprod.core.model.HasName;
import ru.jprod.core.model.dto.DtoMapper;

/**
 * Маппер для {@link Algorithm}
 */
@Component
public class AlgorithmMapper extends DtoMapper<Algorithm>
{
    @Override
    public Map<String, Object> pack(Algorithm from, Map<String, Object> to)
    {
        to.put(HasName.NAME, from.getName());
        return to;
    }

    @Override
    public Algorithm restore(Map<String, Object> map)
    {
        return update(map, new Algorithm());
    }

    @Override
    public Algorithm update(Map<String, Object> from, Algorithm to)
    {
        if (from.containsKey(HasName.NAME))
        {
            to.setName((String)from.get(HasName.NAME));
        }
        return to;
    }
}

