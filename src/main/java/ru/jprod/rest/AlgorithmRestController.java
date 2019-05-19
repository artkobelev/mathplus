package ru.jprod.rest;

import java.util.List;
import java.util.Map;

import ru.jprod.core.model.Algorithm;

public interface AlgorithmRestController
{
    /**
     * Создание конфигурационной единицы
     *
     * @param data - map с полями объекта
     * @return id созданного объекта
     */
    long create(String name, Map<String, Object> data);

    /**
     * Удаление конфигурационной единицы по имени
     *
     * @param name имя
     * @return
     */
    void delete(String name);

    /**
     * Получение списка имен {@link Algorithm}
     *
     * @return - список имен
     */
    List<String> getAll();

    /**
     * Обновление Algorithm по имени
     *
     * @param name - имя Algorithm
     * @param data - map содержащий обновляемые поля
     */
    void update(String name, Map<String, Object> data);
}
