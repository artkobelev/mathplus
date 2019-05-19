package ru.jprod.util.exceptions;

import static java.lang.String.format;

public class ObjectExistsException extends MathplusException
{
    public ObjectExistsException(Class<?> type, String name)
    {
        super(format("Object with type=%s and name=%s already exists", type.getName(), name));
    }

    public ObjectExistsException(String message)
    {
        super(message);
    }

    public ObjectExistsException(String message, Throwable cause)
    {
        super(message, cause);
    }
}