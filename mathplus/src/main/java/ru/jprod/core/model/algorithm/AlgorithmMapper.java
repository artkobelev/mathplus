package ru.jprod.core.model.algorithm;

import java.util.Map;

import org.springframework.stereotype.Component;

import ru.jprod.core.model.Algorithm;
import ru.jprod.core.model.HasName;
import ru.jprod.core.model.dto.DtoMapper;

/**
 * Маппер для {@link Algorithm}
 *
 * @author artem
 * @since 22.05.2019
 */
@Component
public class AlgorithmMapper extends DtoMapper<Algorithm>
{
    private static final String SCRIPT = "script";

    @Override
    public Map<String, Object> pack(Algorithm from, Map<String, Object> to)
    {
        to.put(HasName.NAME, from.getName());
        to.put(SCRIPT, from.getScript());
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
        if (from.containsKey(SCRIPT))
        {
            to.setScript((String)from.get(SCRIPT));
        }
        return to;
    }
}

