package ru.jprod.cases.rest;

import org.junit.Assert;
import org.junit.Test;

import ru.jprod.core.AbstractTestCase;
import ru.jprod.core.model.exec.DSLExec;

/**
 * Тестирование методов REST контроллера выполнения groovy скриптов
 *
 * @author artem
 * @since 27.05.19
 */
public class ExecRestTest extends AbstractTestCase
{
    /**
     * Тестирование REST метода сложения двух чисел
     * <ol>
     * <b>Выполнение действия.</b>
     * <li>Выполняем запрос на сложение двух чисел number1 и number2</li>
     * <br>
     * <b>Проверки.</b>
     * <li>Запрос вернул число number1 + number2</li>
     * </ol>
     */
    @Test
    public void testExec()
    {
        // Выполнение действия
        String script = "return 1";
        String expected = script;

        String result = DSLExec.exec(script);

        // Проверки
        Assert.assertEquals(expected, result);
    }
}