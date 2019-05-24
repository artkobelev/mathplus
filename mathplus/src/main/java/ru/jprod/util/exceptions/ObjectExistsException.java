package ru.jprod.util.exceptions;

import static java.lang.String.format;

/**
 * Исключение генерируется при наличии объекта в системе, которого не должно быть
 *
 * @author akobelev
 * @since 23.05.2019
 */
public class ObjectExistsException extends MathplusException
{
    /**
     * @param type класс объекта
     * @param name имя объекта
     */
    public ObjectExistsException(Class<?> type, String name)
    {
        super(format("Object with type=%s and name=%s already exists", type.getName(), name));
    }

    /**
     * @param message сообщение исключения
     */
    public ObjectExistsException(String message)
    {
        super(message);
    }

    /**
     * @param message сообщение исключения
     * @param cause   родительское исключение
     */
    public ObjectExistsException(String message, Throwable cause)
    {
        super(message, cause);
    }
}