package ru.jprod.core.model.dto;

import java.util.Map;

public interface MapperService
{
    /**
     * Создает {@link Map} свойств объекта.
     *
     * @param object конвертируемый объект
     * @return {@link Map} свойств
     */
    <T extends Exportable> Map<String, Object> packToMap(T object);

    /**
     * Создает новый объект из Map
     *
     * @param map - Map
     * @return {@link Exportable}
     */
    <T extends Exportable> T restore(Map<String, Object> map);

    /**
     * Обновляет поля существующего объекта из {@link Map}
     *
     * @param from   - обновляемые свойства
     * @param object - существующий объект
     * @return {@link Exportable}
     */
    <T extends Exportable> T update(Map<String, Object> from, T object);
}
