package ru.jprod.cleaner;

/**
 * Интерфейс описывает объект, который может быть удален клинером
 *
 * @author artem
 * @since 25.05.2019
 */
public interface ICleanable
{
    /**
     * Очистить объект
     */
    void clean();

    /**
     * Нужно ли очистить объект
     *
     * @return true - объект должен быть удален, false - иначе
     */
    boolean isNeedClean();
}
