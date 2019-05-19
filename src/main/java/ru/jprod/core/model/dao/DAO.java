package ru.jprod.core.model.dao;

import java.util.Collection;
import java.util.List;

import ru.jprod.core.model.HasName;
import ru.jprod.util.exceptions.ObjectExistsException;
import ru.jprod.util.exceptions.ObjectNotFoundException;

/**
 * Базовый интерфейс для DAO
 *
 * @param <T> Класс объектов
 */
public interface DAO<T extends HasName>
{
    /**
     * Проверить отсутствие элемента.
     *
     * @param name имя
     * @throws ObjectExistsException если уже есть.
     */
    void checkAbsent(String name) throws ObjectExistsException;

    /**
     * Количество всех объектов данного типа
     */
    long count();

    /**
     * Удаление объекта
     *
     * @param obj объект
     */
    void delete(T obj);

    /**
     * Получить имена всех существующих объектов
     *
     * @return Список имен
     */
    List<String> getAllNames();

    /**
     * Получение объекта по id
     *
     * @param id -идентификатор
     * @return персистентный объект или null, если объект не существует
     */
    T getById(long id);

    /**
     * Получение объекта по имени
     *
     * @param name имя в {@link HasName}
     * @return персистентный объект или null, если объект на существует
     */
    T getByName(String name);

    /**
     * Получить список объектов по списку имен
     *
     * @param names
     * @return список объектов
     */
    List<T> getExisting(Collection<String> names);

    /**
     * Получение существующего объекта по id
     *
     * @param id -идентификатор
     * @return персистентный объект или null, если объект не существует
     * @throws ObjectNotFoundException если объект на найден
     */
    T getExisting(long id) throws ObjectNotFoundException;

    /**
     * Получение текущего объекта по имени
     *
     * @param name имя
     * @return найденный объект
     * @throws ObjectNotFoundException если объект с имененм не найден.
     */
    T getExisting(String name) throws ObjectNotFoundException;

    /**
     * Сохранить объект в базе
     *
     * @param obj - объект
     * @return Идентификатор сохраненного объекта
     */
    long save(T obj);

    /**
     * Обновление объекта
     *
     * @param obj - изменный объект
     */
    void update(T obj);
}