package ru.jprod.util;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;

/**
 * Класс генерирует случайные данные
 *
 * @author artem
 * @since 25.05.19
 */
public final class GenerateUtils
{
    private static final long DEFAULT_MAX_NUMBER = 1000000L;
    private static final long DEFAULT_MIN_NUMBER = -DEFAULT_MAX_NUMBER;
    private static final int DEFAULT_NAME_LENGTH = 10;
    private static final int DEFAULT_TEXT_LENGTH = 1000;

    private GenerateUtils()
    {
    }

    /**
     * Cгенерировать случайное имя
     *
     * @return имя
     */
    public static String createName()
    {
        return RandomStringUtils.randomAlphabetic(DEFAULT_NAME_LENGTH);
    }

    /**
     * Cгенерировать случайный текст
     *
     * @return имя
     */
    public static String createText()
    {
        return RandomStringUtils.randomAlphabetic(DEFAULT_TEXT_LENGTH);
    }

    /**
     * Сгенерировать случайное целое число
     *
     * @return число
     */
    public static long number()
    {
        long number = RandomUtils.nextLong(0, DEFAULT_MAX_NUMBER);
        if (RandomUtils.nextBoolean())
        {
            number = -number;
        }
        return number;
    }

    /**
     * Сгенерировать случайное целое положительное число
     *
     * @return число
     */
    public static long positiveNumber()
    {
        return RandomUtils.nextLong(1, DEFAULT_MAX_NUMBER);
    }
}
