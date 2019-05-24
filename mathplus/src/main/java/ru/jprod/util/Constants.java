package ru.jprod.util;

/**
 * Класс с константами приложения
 *
 * @author artem
 * @since 19.05.2019
 */
public class Constants
{
    /**
     * Константы сущностей БД
     */
    public static class EntityConstants
    {
        /**
         * Максимальная длина имени сущности
         */
        public static final int NAME_MAX_LENGTH = 50;

        /**
         * Минимальная длина имени сущности
         */
        public static final int NAME_MIN_LENGTH = 3;

        /**
         * Формат имени сущности
         */
        public static final String NAME_REGEX = "^[a-zA-Z0-9_-]*\\$*[a-zA-Z0-9_-]*$";
    }
}
