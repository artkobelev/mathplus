package ru.jprod.cases.rest;

import static com.jayway.restassured.RestAssured.given;
import static com.jayway.restassured.http.ContentType.JSON;
import static org.apache.http.HttpStatus.SC_OK;

import java.util.Set;

import org.junit.Assert;
import org.junit.Test;

import com.google.common.collect.Sets;

import ru.jprod.rest.ArithmeticConstants;
import ru.jprod.core.AbstractTestCase;
import ru.jprod.core.config.Configuration;
import ru.jprod.util.GenerateUtils;

/**
 * Тестирование методов REST контроллера выполнения арифметических операций
 *
 * @author artem
 * @since 25.05.19
 */
public class ArithmeticRestTest extends AbstractTestCase
{
    private static final double DELTA = Configuration.get().getDelta();

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
    public void testAdd()
    {
        // Выполнение действия
        double number1 = GenerateUtils.createDouble();
        double number2 = GenerateUtils.createDouble();

        //@formatter:off
        double result = given()
            .parameter("number1", number1)
            .parameter("number2", number2)
        .expect()
            .statusCode(SC_OK)
            .contentType(JSON)
        .when()
            .get(ArithmeticConstants.ADD_URI)
        .then()
            .extract()
            .as(Double.class);
        //@formatter:on

        // Проверки
        Assert.assertEquals(number1 + number2, result, DELTA);
    }

    /**
     * Тестирование REST метода нахождения среднего значения набора чисел
     * <ol>
     * <b>Выполнение действия.</b>
     * <li>Выполняем запрос на нахождение среднего значения набора чисел numbers</li>
     * <br>
     * <b>Проверки.</b>
     * <li>Запрос вернул среднего значения чисел в numbers</li>
     * </ol>
     */
    @Test
    public void testAvg()
    {
        // Выполнение действия
        Set<Double> numbers = Sets.newHashSet(
                GenerateUtils.createDouble(),
                GenerateUtils.createDouble(),
                GenerateUtils.createDouble()
        );

        //@formatter:off
        double result = given()
            .parameter("numbers", numbers)
        .expect()
            .statusCode(SC_OK)
            .contentType(JSON)
        .when()
            .get(ArithmeticConstants.AVG_URI)
        .then()
            .extract()
            .as(Double.class);
        //@formatter:on

        // Проверки
        Assert.assertEquals(numbers.stream().mapToDouble(val -> val).average().orElse(0.0), result, DELTA);
    }

    /**
     * Тестирование REST метода декрементирования числа
     * <ol>
     * <b>Выполнение действия.</b>
     * <li>Выполняем запрос на уменьшение числа на единицу со значением number</li>
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

        //@formatter:off
        long result = given()
            .pathParam(ArithmeticConstants.PATH_NUMBER, number)
        .expect()
            .statusCode(SC_OK)
            .contentType(JSON)
        .when()
            .get(ArithmeticConstants.DEC_URI)
        .then()
            .extract()
            .as(Long.class);
        //@formatter:on

        // Проверки
        Assert.assertEquals(number - 1, result);
    }

    /**
     * Тестирование REST метода деления двух чисел
     * <ol>
     * <b>Выполнение действия.</b>
     * <li>Выполняем запрос на деление двух чисел number1 и number2</li>
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

        //@formatter:off
        double result = given()
            .parameter("number1", number1)
            .parameter("number2", number2)
        .expect()
            .statusCode(SC_OK)
            .contentType(JSON)
        .when()
            .get(ArithmeticConstants.DIV_URI)
        .then()
            .extract()
            .as(Double.class);
        //@formatter:on

        // Проверки
        Assert.assertEquals(number1 / number2, result, DELTA);
    }

    /**
     * Тестирование REST метода инкрементирования числа
     * <ol>
     * <b>Выполнение действия.</b>
     * <li>Выполняем запрос на увеличение числа на единицу со значением number</li>
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

        //@formatter:off
        long result = given()
            .pathParam(ArithmeticConstants.PATH_NUMBER, number)
        .expect()
            .statusCode(SC_OK)
            .contentType(JSON)
        .when()
            .get(ArithmeticConstants.INC_URI)
        .then()
            .extract()
            .as(Long.class);
        //@formatter:on

        // Проверки
        Assert.assertEquals(number + 1, result);
    }

    /**
     * Тестирование REST метода умножения двух чисел
     * <ol>
     * <b>Выполнение действия.</b>
     * <li>Выполняем запрос на умножение двух чисел number1 и number2</li>
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

        //@formatter:off
        double result = given()
            .parameter("number1", number1)
            .parameter("number2", number2)
        .expect()
            .statusCode(SC_OK)
            .contentType(JSON)
        .when()
            .get(ArithmeticConstants.MUL_URI)
        .then()
            .extract()
            .as(Double.class);
        //@formatter:on

        // Проверки
        Assert.assertEquals(number1 * number2, result, DELTA);
    }

    /**
     * Тестирование REST метода вычитания двух чисел
     * <ol>
     * <b>Выполнение действия.</b>
     * <li>Выполняем запрос на вычитание двух чисел number1 и number2</li>
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

        //@formatter:off
        double result = given()
            .parameter("number1", number1)
            .parameter("number2", number2)
        .expect()
            .statusCode(SC_OK)
            .contentType(JSON)
        .when()
            .get(ArithmeticConstants.SUB_URI)
        .then()
            .extract()
            .as(Double.class);
        //@formatter:on

        // Проверки
        Assert.assertEquals(number1 - number2, result, DELTA);
    }

    /**
     * Тестирование REST метода суммирования набора чисел
     * <ol>
     * <b>Выполнение действия.</b>
     * <li>Выполняем запрос на суммирование набора чисел numbers</li>
     * <br>
     * <b>Проверки.</b>
     * <li>Запрос вернул сумму чисел в numbers</li>
     * </ol>
     */
    @Test
    public void testSum()
    {
        // Выполнение действия
        Set<Double> numbers = Sets.newHashSet(
                GenerateUtils.createDouble(),
                GenerateUtils.createDouble(),
                GenerateUtils.createDouble()
        );

        //@formatter:off
        double result = given()
            .parameter("numbers", numbers)
        .expect()
            .statusCode(SC_OK)
            .contentType(JSON)
        .when()
            .get(ArithmeticConstants.SUM_URI)
        .then()
            .extract()
            .as(Double.class);
        //@formatter:on

        // Проверки
        Assert.assertEquals(numbers.stream().mapToDouble(v -> v).sum(), result, DELTA);
    }
}
