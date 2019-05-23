package ru.jprod.core.model.dto;

import java.lang.reflect.ParameterizedType;
import java.util.Map;

/**
 * Маппер {@link Exportable} объектов из/в {@link DtObject}
 *
 * @param <T>
 * @author artem
 * @since 20.05.2019
 */
public abstract class DtoMapper<T extends Exportable>
{
    public static final String TYPE = "type";

    /**
     * Получение класса (T) экспортируемого объекта
     *
     * @return поддерживаемый тип объекта маппера
     */
    public Class<T> getType()
    {
        return (Class<T>)((ParameterizedType)getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    /**
     * Наполняет {@link Map} данными из from
     *
     * @param from - объект реализующий {@link Exportable}
     * @return {@link DtObject}
     */
    public abstract Map<String, Object> pack(T from, Map<String, Object> to);

    /**
     * Создает новый объект из Map
     *
     * @param map - Map
     * @return {@link Exportable}
     */
    public abstract T restore(Map<String, Object> map);

    /**
     * Обновляет поля существующего объекта из {@link Map}
     *
     * @param from - обновляемые свойства
     * @param to   - существующий объект
     * @return {@link Exportable}
     */
    public abstract T update(Map<String, Object> from, T to);
}
