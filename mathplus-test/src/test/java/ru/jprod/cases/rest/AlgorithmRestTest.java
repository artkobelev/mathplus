package ru.jprod.cases.rest;

import java.util.Set;

import org.junit.Assert;
import org.junit.Test;

import com.google.common.collect.Sets;

import ru.jprod.core.AbstractTestCase;
import ru.jprod.core.model.algorithm.Algorithm;
import ru.jprod.core.model.algorithm.DAOAlgorithm;
import ru.jprod.core.model.algorithm.DSLAlgorithm;
import ru.jprod.util.GenerateUtils;

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
     * <li>Создать модель алгоритма с тестовыми данными</li>
     * <li>Выполнить запрос на создание алгоритма</li>
     * <br>
     * <b>Проверки.</b>
     * <li>Алгоритм создан</li>
     * </ol>
     */
    @Test
    public void testCreate()
    {
        // Выполнение действия
        Algorithm algorithm = DAOAlgorithm.create();
        DSLAlgorithm.create(algorithm);

        // Проверки
        DSLAlgorithm.assertExists(algorithm.getName());
    }

    /**
     * Тестирование REST метода удаления алгоритма
     * <ol>
     * <b>Выполнение действия.</b>
     * <li>Создать модель алгоритма с тестовыми данными</li>
     * <li>Выполнить запрос на создание алгоритма</li>
     * <li>Выполнить запрос на удаление алгоритма</li>
     * <li>Выполнить запрос на получение алгоритма</li>
     * <br>
     * <b>Проверки.</b>
     * <li>Запрос получение алгоритма вернул объект с тестовыми данными</li>
     * </ol>
     */
    @Test
    public void testDelete()
    {
        // Выполнение действия
        Algorithm algorithm = DAOAlgorithm.create();
        DSLAlgorithm.create(algorithm);
        DSLAlgorithm.delete(algorithm);

        // Проверки
        DSLAlgorithm.assertAbsent(algorithm.getName());
    }

    /**
     * Тестирование REST метода олучения алгоритма
     * <ol>
     * <b>Выполнение действия.</b>
     * <li>Создать модель алгоритма с тестовыми данными</li>
     * <li>Выполнить запрос на создание алгоритма</li>
     * <li>Выполнить запрос на получение алгоритма</li>
     * <br>
     * <b>Проверки.</b>
     * <li>Запрос получение алгоритма вернул объект с тестовыми данными</li>
     * </ol>
     */
    @Test
    public void testGet()
    {
        // Выполнение действия
        Algorithm algorithm = DAOAlgorithm.create();
        DSLAlgorithm.create(algorithm);
        Algorithm actual = DSLAlgorithm.get(algorithm.getName());

        // Проверки
        Assert.assertEquals(algorithm.getName(), actual.getName());
        Assert.assertEquals(algorithm.getScript(), actual.getScript());
    }

    /**
     * Тестирование REST метода получения всех имен алгоритмов в системе
     * <ol>
     * <b>Выполнение действия.</b>
     * <li>Создать модель algorithm1 с тестовыми данными</li>
     * <li>Создать модель algorithm2 с тестовыми данными</li>
     * <li>Выполнить запрос на создание алгоритма algorithm1</li>
     * <li>Выполнить запрос на создание алгоритма algorithm2</li>
     * <li>Выполнить запрос на получения всех имен алгоритмов в системе</li>
     * <br>
     * <b>Проверки.</b>
     * <li>Запрос вернул список, в котором содержатся имена алгоритмов algorithm1 и algorithm2</li>
     * </ol>
     */
    @Test
    public void testGetAll()
    {
        // Выполнение действия
        Algorithm algorithm1 = DAOAlgorithm.create();
        Algorithm algorithm2 = DAOAlgorithm.create();
        DSLAlgorithm.create(algorithm1);
        DSLAlgorithm.create(algorithm2);

        // Проверки
        Set<String> expectedNames = Sets.newHashSet(algorithm1.getName(), algorithm2.getName());
        Set<String> actualNames = Sets.newHashSet(DSLAlgorithm.getAll());
        Assert.assertEquals(expectedNames, actualNames);
    }

    /**
     * Тестирование REST метода изменения алгоритма
     * <ol>
     * <b>Выполнение действия.</b>
     * <li>Создать модель алгоритма algorithm с параметрами
     * <ul>
     * <li>имя: name1</li>
     * <li>скрипт: script1</li>
     * <li></li>
     * </ul>
     * /li>
     * <li>Выполнить запрос на создание алгоритма</li>
     * <li>Выполнить запрос на изменение алгоритма с параметрами:
     * <ul>
     * <li>имя: name2</li>
     * <li>script: script2</li>
     * <li></li>
     * </ul>
     * </li>
     * <li>Выполнить запрос на получение алгоритма с именем name2</li>
     * <br>
     * <b>Проверки.</b>
     * <li>Имя и скрипт алгоритмы равны name2 и script2 соответственно</li>
     * </ol>
     */
    @Test
    public void testUpdate()
    {
        // Выполнение действия
        String name = GenerateUtils.createName();
        Algorithm algorithm = DAOAlgorithm.create(name);
        DSLAlgorithm.create(algorithm);

        algorithm.setName(GenerateUtils.createName());
        algorithm.setScript(GenerateUtils.createText());
        DSLAlgorithm.update(name, algorithm);

        // Проверки
        Algorithm updatedAlgorithm = DSLAlgorithm.get(algorithm.getName());
        Assert.assertEquals(algorithm.getName(), updatedAlgorithm.getName());
        Assert.assertEquals(algorithm.getScript(), updatedAlgorithm.getScript());
    }
}
