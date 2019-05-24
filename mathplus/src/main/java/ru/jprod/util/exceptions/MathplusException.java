package ru.jprod.util.exceptions;

/**
 * Исключение генерируется при возникновении ошибок в приложении
 *
 * @author akobelev
 * @since 23.05.2019
 */
public class MathplusException extends RuntimeException
{
    /**
     * @param message сообщение исключения
     */
    public MathplusException(String message)
    {
        super(message);
    }

    /**
     * @param message сообщение исключения
     * @param cause   родителськое исключение
     */
    public MathplusException(String message, Throwable cause)
    {
        super(message, cause);
    }
}
