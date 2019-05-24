package ru.jprod.core.model;

/**
 * Интерфейс объектов, которые имеют имя.
 */
public interface HasName
{
    String NAME = "name";

    /**
     * Получить имя
     *
     * @return имя
     */
    String getName();
}
