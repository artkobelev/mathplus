package ru.jprod.cases.script;

import java.util.List;
import java.util.Set;

import org.junit.Assert;
import org.junit.Test;

import com.google.common.base.Joiner;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

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

    /**
     * Тестирование script API метода api.arithm.average
     * <ol>
     * <b>Выполнение действия.</b>
     * <li>Выполняем exec запрос со скриптом:
     * <pre>
     *     api.arithm.average(numbers)
     * </pre>
     * </li>
     * <br>
     * <b>Проверки.</b>
     * <li>Запрос вернул среднее значение numbers</li>
     * </ol>
     */
    @Test
    public void testAverage()
    {
        // Выполнение действия
        Set<Double> numbers = Sets.newHashSet(
                GenerateUtils.createDouble(),
                GenerateUtils.createDouble(),
                GenerateUtils.createDouble());

        String script = String.format("api.arithm.average([%s])", Joiner.on(',').join(numbers));
        double result = DSLExec.exec(script, Double.class);

        // Проверки
        Assert.assertEquals(numbers.stream().mapToDouble(val -> val).average().orElse(0.0), result, DELTA);
    }

    /**
     * Тестирование script API метода api.arithm.dec
     * <ol>
     * <b>Выполнение действия.</b>
     * <li>Выполняем exec запрос со скриптом:
     * <pre>
     *     api.arithm.dec(number)
     * </pre>
     * </li>
     * <br>
     * <b>Проверки.</b>
     * <li>Запрос вернул число number - 1</li>
     * </ol>
     */
    @Test
    public void testDec()
    {
        // Выполнение действия
        long number = GenerateUtils.createLong();

        String script = String.format("api.arithm.dec(%s)", number);
        long result = DSLExec.exec(script, Long.class);

        // Проверки
        Assert.assertEquals(number - 1, result);
    }

    /**
     * Тестирование script API метода api.arithm.div
     * <ol>
     * <b>Выполнение действия.</b>
     * <li>Выполняем exec запрос со скриптом:
     * <pre>
     *     api.arithm.div(number1, number2)
     * </pre>
     * </li>
     * <br>
     * <b>Проверки.</b>
     * <li>Запрос вернул число number1 / number2</li>
     * </ol>
     */
    @Test
    public void testDiv()
    {
        // Выполнение действия
        double number1 = GenerateUtils.createDouble();
        double number2 = GenerateUtils.createPositiveDouble();

        String script = String.format("api.arithm.div(%s, %s)", number1, number2);
        double result = DSLExec.exec(script, Double.class);

        // Проверки
        Assert.assertEquals(number1 / number2, result, DELTA);
    }

    /**
     * Тестирование script API метода api.arithm.inc
     * <ol>
     * <b>Выполнение действия.</b>
     * <li>Выполняем exec запрос со скриптом:
     * <pre>
     *     api.arithm.inc(number)
     * </pre>
     * </li>
     * <br>
     * <b>Проверки.</b>
     * <li>Запрос вернул число number + 1</li>
     * </ol>
     */
    @Test
    public void testInc()
    {
        // Выполнение действия
        long number = GenerateUtils.createLong();

        String script = String.format("api.arithm.inc(%s)", number);
        long result = DSLExec.exec(script, Long.class);

        // Проверки
        Assert.assertEquals(number + 1, result);
    }

    /**
     * Тестирование script API метода api.arithm.mul
     * <ol>
     * <b>Выполнение действия.</b>
     * <li>Выполняем exec запрос со скриптом:
     * <pre>
     *     api.arithm.mul(number1, number2)
     * </pre>
     * </li>
     * <br>
     * <b>Проверки.</b>
     * <li>Запрос вернул число number1 * number2</li>
     * </ol>
     */
    @Test
    public void testMul()
    {
        // Выполнение действия
        double number1 = GenerateUtils.createDouble();
        double number2 = GenerateUtils.createDouble();

        String script = String.format("api.arithm.mul(%s, %s)", number1, number2);
        double result = DSLExec.exec(script, Double.class);

        // Проверки
        Assert.assertEquals(number1 * number2, result, DELTA);
    }

    /**
     * Тестирование script API метода api.arithm.neg
     * <ol>
     * <b>Выполнение действия.</b>
     * <li>Выполняем exec запрос со скриптом:
     * <pre>
     *     api.arithm.neg(number)
     * </pre>
     * </li>
     * <br>
     * <b>Проверки.</b>
     * <li>Запрос вернул число -number</li>
     * </ol>
     */
    @Test
    public void testNeg()
    {
        // Выполнение действия
        long number = GenerateUtils.createLong();

        String script = String.format("api.arithm.neg(%s)", number);
        long result = DSLExec.exec(script, Long.class);

        // Проверки
        Assert.assertEquals(-number, result);
    }

    /**
     * Тестирование script API метода api.arithm.sub
     * <ol>
     * <b>Выполнение действия.</b>
     * <li>Выполняем exec запрос со скриптом:
     * <pre>
     *     api.arithm.sub(number1, number2)
     * </pre>
     * </li>
     * <br>
     * <b>Проверки.</b>
     * <li>Запрос вернул число number1 - number2</li>
     * </ol>
     */
    @Test
    public void testSub()
    {
        // Выполнение действия
        double number1 = GenerateUtils.createDouble();
        double number2 = GenerateUtils.createDouble();

        String script = String.format("api.arithm.sub(%s, %s)", number1, number2);
        double result = DSLExec.exec(script, Double.class);

        // Проверки
        Assert.assertEquals(number1 - number2, result, DELTA);
    }

    /**
     * Тестирование script API метода api.arithm.sum
     * <ol>
     * <b>Выполнение действия.</b>
     * <li>Выполняем exec запрос со скриптом:
     * <pre>
     *     api.arithm.sum(numbers)
     * </pre>
     * </li>
     * <br>
     * <b>Проверки.</b>
     * <li>Запрос вернул сумму numbers</li>
     * </ol>
     */
    @Test
    public void testSum()
    {
        // Выполнение действия
        List<Double> numbers = Lists.newArrayList(
                GenerateUtils.createDouble(),
                GenerateUtils.createDouble(),
                GenerateUtils.createDouble());

        String script = String.format("api.arithm.sum([%s])", Joiner.on(',').join(numbers));
        double result = DSLExec.exec(script, Double.class);

        // Проверки
        Assert.assertEquals(numbers.stream().mapToDouble(val -> val).sum(), result, DELTA);
    }
}