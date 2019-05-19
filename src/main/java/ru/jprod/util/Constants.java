package ru.jprod.util;

/**
 * Класс с константами приложения
 *
 * @author akobelev
 * @since 19.05.2019
 */
public class Constants
{
    public static class EntityConstants
    {
        public static final int NAME_MAX_LENGTH = 50;
        public static final int NAME_MIN_LENGTH = 3;
        public static final String NAME_REGEX = "^[a-zA-Z0-9_-]*\\$*[a-zA-Z0-9_-]*$";
    }
}
