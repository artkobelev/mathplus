package ru.jprod.util.exceptions;

import static java.lang.String.format;

public class ObjectNotFoundException extends MathplusException
{
    public ObjectNotFoundException(Class<?> type, long id)
    {
        super(format("Object with type=%s and id=%d not found", type.getName(), id));
    }

    public ObjectNotFoundException(Class<?> type, String name)
    {
        super(format("Object with type=%s and name=%s not found", type.getName(), name));
    }

    public ObjectNotFoundException(String message)
    {
        super(message);
    }

    public ObjectNotFoundException(String message, Throwable cause)
    {
        super(message, cause);
    }
}