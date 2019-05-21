package ru.jprod.core.model.dto;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.Collections;
import java.util.Map;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.common.collect.ForwardingMap;

/**
 * Объект переноса данных основанный на {@link ForwardingMap}<br>
 * Десериализация из JSON: {@link DtoDeserializer}
 */
@JsonDeserialize(using = DtoDeserializer.class)
public final class DtObject extends ForwardingMap<String, Object>
{
    private Map<String, Object> unmodifibleWrapper;

    private DtObject(Map<String, Object> data)
    {
        checkNotNull(data);
        unmodifibleWrapper = Collections.unmodifiableMap(data);
    }

    /**
     * Создать {@link DtObject} на основе мапы свойств.
     *
     * @param properties - свойства
     * @return {@link DtObject}
     */
    public static DtObject fromMap(Map<String, Object> properties)
    {
        return new DtObject(properties);
    }

    @Override
    protected Map<String, Object> delegate()
    {
        return unmodifibleWrapper;
    }
}
