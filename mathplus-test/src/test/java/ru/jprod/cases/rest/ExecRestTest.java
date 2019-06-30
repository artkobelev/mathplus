package ru.jprod.cases.rest;

import java.util.Map;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.google.common.collect.Maps;

import ru.jprod.core.AbstractTestCase;
import ru.jprod.core.model.algorithm.Algorithm;
import ru.jprod.core.model.algorithm.DAOAlgorithm;
import ru.jprod.core.model.algorithm.DSLAlgorithm;
import ru.jprod.core.model.exec.DSLExec;
import ru.jprod.util.GenerateUtils;

/**
 * Тестирование методов REST контроллера выполнения скриптов
 *
 * @author artem
 * @since 01.06.19
 */
public class ExecRestTest extends AbstractTestCase
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
        Assertions.assertEquals(number1 + number2, result);
    }

    /**
     * Тестирование API метода exec/algth
     * <ol>
     * <b>Выполнение действия.</b>
     * <li>Создать алгоритм algorithm со скриптом:
     * <pre>
     *     int fib(int n) {
     *         n in [0,1] ? n : fib(n-1) + fib(n-2)
     *     }
     *     return fib (argument)
     * </pre>
     * </li>
     * <li>Выполняем exec запрос на выполнение алгоритма algorithm с контекстом: "argument" = 10</li>
     * <br>
     * <b>Проверки.</b>
     * <li>Запрос вернул значение функции Фибоначчи от argument (55)</li>
     * </ol>
     */
    @Test
    public void testExecAlgorithm()
    {

        // Выполнение действия
        Algorithm algorithm = DAOAlgorithm.create();
        algorithm.setScript("int fib(int n) { n in [0,1] ? n : fib(n-1) + fib(n-2) }; return fib (argument)");
        DSLAlgorithm.create(algorithm);

        Map<String, Object> context = Maps.newHashMap();
        context.put("argument", 10);
        long result = DSLExec.execAlgorithm(algorithm.getName(), context, Long.class);

        // Проверки
        Assertions.assertEquals(55, result);
    }
}
