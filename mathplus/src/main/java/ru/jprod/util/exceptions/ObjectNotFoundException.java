package ru.jprod.util.exceptions;

import static java.lang.String.format;

/**
 * Исключение генерируется при отсутствии объекта в системе
 *
 * @author akobelev
 * @since 23.05.2019
 */
public class ObjectNotFoundException extends MathplusException
{
    /**
     * @param type класс объекта
     * @param id   идентификатор объекта
     */
    public ObjectNotFoundException(Class<?> type, long id)
    {
        super(format("Object with type=%s and id=%d not found", type.getName(), id));
    }

    /**
     * @param type класс объекта
     * @param name имя объекта
     */
    public ObjectNotFoundException(Class<?> type, String name)
    {
        super(format("Object with type=%s and name=%s not found", type.getName(), name));
    }

    /**
     * @param message сообщение исключения
     */
    public ObjectNotFoundException(String message)
    {
        super(message);
    }

    /**
     * @param message сообщение исключения
     * @param cause   родительское исключение
     */
    public ObjectNotFoundException(String message, Throwable cause)
    {
        super(message, cause);
    }
}