package ru.jprod.core.model.algorithm;

import ru.jprod.core.model.Model;

/**
 * Модель алгоритма
 *
 * @author artem
 * @since 25.05.19
 */
public class Algorithm extends Model
{
    public static final String NAME_PROPERTY = "name";
    public static final String SCRIPT_PROPERTY = "script";

    @Override
    public void clean()
    {
        DSLAlgorithm.delete(this);
    }

    /**
     * Получить имя
     *
     * @return имя
     */
    public String getName()
    {
        return (String)properties.get(NAME_PROPERTY);
    }

    /**
     * Получить скрипт
     *
     * @return скрипт
     */
    public String getScript()
    {
        return (String)properties.get(SCRIPT_PROPERTY);
    }

    /**
     * Установить имя
     *
     * @return имя
     */
    public void setName(String name)
    {
        properties.put(NAME_PROPERTY, name);
    }

    /**
     * Установить скрипт
     *
     * @return скрипт
     */
    public void setScript(String script)
    {
        properties.put(SCRIPT_PROPERTY, script);
    }
}
