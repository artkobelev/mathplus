package ru.jprod.cases.rest;

import org.junit.Assert;
import org.junit.Test;

import ru.jprod.core.model.exec.DSLExec;
import ru.jprod.util.GenerateUtils;

/**
 * Тестирование методов REST контроллера выполнения скриптов
 *
 * @author artem
 * @since 01.06.19
 */
public class ExecRestTest
{
    /**
     * Тестирование API метода exec
     * <ol>
     * <b>Выполнение действия.</b>
     * <li>Выполняем exec запрос со скриптом:
     * <pre>
     *     return  number1 + number2
     * </pre>
     * </li>
     * <br>
     * <b>Проверки.</b>
     * <li>Запрос вернул число number1 + number2</li>
     * </ol>
     */
    @Test
    public void testExec()
    {
        // Выполнение действия
        long number1 = GenerateUtils.createLong();
        long number2 = GenerateUtils.createLong();

        String script = String.format("return %s + %s", number1, number2);
        long result = DSLExec.exec(script, Long.class);

        // Проверки
        Assert.assertEquals(number1 + number2, result);
    }
}
