package ru.jprod.cases.rest;

import static com.jayway.restassured.RestAssured.given;
import static com.jayway.restassured.http.ContentType.JSON;
import static org.apache.http.HttpStatus.SC_OK;

import org.junit.Assert;
import org.junit.Test;

import ru.jprod.ArithmeticConstants;
import ru.jprod.core.AbstractTestCase;
import ru.jprod.util.GenerateUtils;

/**
 * Тестирование методов REST контроллера выполнения арифметических операций
 *
 * @author artem
 * @since 25.05.19
 */
public class ArithmeticRestTest extends AbstractTestCase
{
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
        long number = GenerateUtils.number();

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

        Assert.assertEquals(number - 1, result);
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
        long number = GenerateUtils.number();

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

        Assert.assertEquals(number + 1, result);
    }
}
