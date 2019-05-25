package ru.jprod.core.model;

import java.util.Map;

import com.google.common.collect.Maps;

import ru.jprod.cleaner.ICleanable;

/**
 * Базовое описание модели
 *
 * @author artem
 * @since 25.05.19
 */
public class Model implements ICleanable
{
    protected Map<String, Object> properties = Maps.newHashMap();
    private boolean needClean = false;

    @Override
    public void clean()
    {
    }

    public Map<String, Object> getProperties()
    {
        return properties;
    }

    @Override
    public boolean isNeedClean()
    {
        return needClean;
    }

    public void setNeedClean(boolean needClean)
    {
        this.needClean = needClean;
    }
}
