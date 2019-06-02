package ru.jprod.core.model.exec;

import static com.jayway.restassured.RestAssured.given;
import static org.apache.http.HttpStatus.SC_OK;

import java.util.Map;

import com.jayway.restassured.http.ContentType;

import ru.jprod.rest.AlgorithmConstants;
import ru.jprod.rest.ExecConstants;

/**
 * DSL для работы со скриптами
 *
 * @author artem
 * @since 27.05.19
 */
public class DSLExec
{
    /**
     * Выполнить скрипт
     *
     * @param script тело скрипта
     * @param clazz  класс
     * @param <T>
     * @return результат выполнения
     */
    public static <T> T exec(String script, Class<T> clazz)
    {
        //@formatter:off
        return given()
            .contentType(ContentType.JSON)
            .body(script)
        .expect()
            .statusCode(SC_OK)
        .when()
            .post(ExecConstants.ROOT_URI)
        .then()
            .extract()
            .as(clazz);
        //@formatter:on
    }

    /**
     * Выполнить скрипт
     *
     * @param script тело скрипта
     * @return результат выполнения
     */
    public static String exec(String script)
    {
        return exec(script, String.class);
    }

    /**
     * Выполнить алгоритм
     *
     * @param name    тело скрипта
     * @param context контекст
     * @return результат выполнения
     */
    public static String execAlgorithm(String name, Map<String, Object> context)
    {
        return execAlgorithm(name, context, String.class);
    }

    /**
     * Выполнить алгоритм
     *
     * @param name    тело скрипта
     * @param context контекст
     * @param clazz   класс
     * @param <T>
     * @return результат выполнения
     */
    public static <T> T execAlgorithm(String name, Map<String, Object> context, Class<T> clazz)
    {
        //@formatter:off
        return given()
            .pathParam(AlgorithmConstants.PATH_NAME, name)
            .contentType(ContentType.JSON)
            .body(context)
        .expect()
            .statusCode(SC_OK)
        .when()
            .post(ExecConstants.EXEC_ALGORITHM_URI)
        .then()
            .extract()
            .as(clazz);
        //@formatter:on
    }
}
