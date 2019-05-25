package ru.jprod.cases.rest;

import org.junit.Assert;
import org.junit.Test;

import ru.jprod.core.AbstractTestCase;
import ru.jprod.core.model.algorithm.Algorithm;
import ru.jprod.core.model.algorithm.DAOAlgorithm;
import ru.jprod.core.model.algorithm.DSLAlgorithm;

/**
 * Тестирование методов REST контроллера работы с алгоритмом
 *
 * @author artem
 * @since 25.05.19
 */
public class AlgorithmRestTest extends AbstractTestCase
{
    /**
     * Тестирование REST метода добавления алгоритма
     * <ol>
     * <b>Выполнение действия.</b>
     * <li>Создать объект алгоритма</li>
     * <br>
     * <b>Проверки.</b>
     * <li>Алгоритм создался и запрос вернул идентификатор объекта</li>
     * </ol>
     */
    @Test
    public void testCreate()
    {
        Algorithm algorithm = DAOAlgorithm.create();

        DSLAlgorithm.create(algorithm);
        Algorithm actual = DSLAlgorithm.get(algorithm.getName());

        //TODO реализовать equals в алгоритме
        Assert.assertEquals(algorithm.getScript(), actual.getScript());
    }
}
