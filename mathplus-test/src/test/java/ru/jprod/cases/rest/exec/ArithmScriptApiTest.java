package ru.jprod.cases.rest.exec;

import org.junit.Assert;
import org.junit.Test;

import ru.jprod.core.AbstractTestCase;
import ru.jprod.core.config.Configuration;
import ru.jprod.core.model.exec.DSLExec;
import ru.jprod.util.GenerateUtils;

/**
 * Тестирование скриптового API api.arithm
 *
 * @author artem
 * @since 27.05.19
 */
public class ArithmScriptApiTest extends AbstractTestCase
{
    private static final double DELTA = Configuration.get().getDelta();

    /**
     * Тестирование script API метода api.arithm.add
     * <ol>
     * <b>Выполнение действия.</b>
     * <li>Выполняем exec запрос со скриптом:
     * <pre>
     *     api.arithm.add(number1, number2)
     * </pre>
     * </li>
     * <br>
     * <b>Проверки.</b>
     * <li>Запрос вернул число number1 + number2</li>
     * </ol>
     */
    @Test
    public void testAdd()
    {
        // Выполнение действия
        double number1 = GenerateUtils.createDouble();
        double number2 = GenerateUtils.createDouble();

        String script = String.format("api.arithm.add(%s, %s)", number1, number2);
        double result = DSLExec.exec(script, Double.class);

        // Проверки
        Assert.assertEquals(number1 + number2, result, DELTA);
    }
}