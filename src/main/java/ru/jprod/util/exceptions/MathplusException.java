package ru.jprod.util.exceptions;

public class MathplusException extends RuntimeException
{
    public MathplusException(String message)
    {
        super(message);
    }

    public MathplusException(String message, Throwable cause)
    {
        super(message, cause);
    }
}
