package ru.jprod.core.model.algorithm;

import static com.jayway.restassured.RestAssured.given;
import static org.apache.http.HttpStatus.SC_CREATED;
import static org.apache.http.HttpStatus.SC_INTERNAL_SERVER_ERROR;
import static org.apache.http.HttpStatus.SC_OK;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;
import static ru.jprod.core.model.algorithm.Algorithm.NAME_PROPERTY;
import static ru.jprod.core.model.algorithm.Algorithm.SCRIPT_PROPERTY;

import java.util.List;
import java.util.Map;

import com.jayway.restassured.http.ContentType;

import ru.jprod.rest.AlgorithmConstants;

/**
 * DSL для объекта алгоритма
 *
 * @author artem
 * @since 25.05.19
 */
public class DSLAlgorithm
{
    /**
     * Проверить, что алгоритм отсутствует в системе
     *
     * @param name имя алгоритма
     */
    public static void assertAbsent(String name)
    {
        //@formatter:off
        given()
             .pathParam(AlgorithmConstants.PATH_NAME, name)
        .expect()
             .statusCode(SC_INTERNAL_SERVER_ERROR)
             .content("message", equalTo(String.format(AlgorithmConstants.OBJECT_NOT_FOUND_MESSAGE, name)))
        .when()
             .get(AlgorithmConstants.ALGORITHM_URI);
        //@formatter:on
    }

    /**
     * Проверить, что алгоритм присутствует в системе
     *
     * @param name имя алгоритма
     */
    public static void assertExists(String name)
    {
        //@formatter:off
        given()
             .pathParam(AlgorithmConstants.PATH_NAME, name)
        .expect()
             .statusCode(SC_OK)
             .content(NAME_PROPERTY, notNullValue())
             .content(NAME_PROPERTY, equalTo(name))
        .when()
             .get(AlgorithmConstants.ALGORITHM_URI);
        //@formatter:on
    }

    /**
     * Добавить алгоритм
     *
     * @param algorithm модель алгоритма
     * @return идентификатор объекта в БД
     */
    public static long create(Algorithm algorithm)
    {
        //@formatter:off
        long id = given()
            .pathParam(AlgorithmConstants.PATH_NAME, algorithm.getName())
            .contentType(ContentType.JSON)
            .body(algorithm.getProperties())
        .expect()
            .statusCode(SC_CREATED)
        .when()
            .post(AlgorithmConstants.ALGORITHM_URI)
        .then()
            .statusCode(SC_CREATED)
            .extract()
            .as(Long.class);
        //@formatter:on
        algorithm.setNeedClean(true);
        return id;
    }

    /**
     * Удалить алгоритм
     *
     * @param algorithm модель алгоритма
     */
    public static void delete(Algorithm algorithm)
    {
        //@formatter:off
        given()
            .pathParam(AlgorithmConstants.PATH_NAME, algorithm.getName())
        .expect()
            .statusCode(SC_OK)
        .when()
            .delete(AlgorithmConstants.ALGORITHM_URI);
        //@formatter:on
        algorithm.setNeedClean(false);
    }

    /**
     * Получить алгоритм по имени
     *
     * @param name имя
     * @return модель алгоритма
     */
    @SuppressWarnings("unchecked")
    public static Algorithm get(String name)
    {
        //@formatter:off
        Map<String, String> params =  given()
             .pathParam(AlgorithmConstants.PATH_NAME, name)
        .expect()
             .statusCode(SC_OK)
             .content(NAME_PROPERTY, notNullValue())
             .content(SCRIPT_PROPERTY, notNullValue())
        .when()
             .get(AlgorithmConstants.ALGORITHM_URI)
        .then()
             .extract()
             .as(Map.class);
        //@formatter:on

        Algorithm algorithm = DAOAlgorithm.create(params.get(NAME_PROPERTY));
        algorithm.setScript(params.get(SCRIPT_PROPERTY));
        return algorithm;
    }

    /**
     * Получить имена всех алгоритмов в системе
     *
     * @return список имен
     */
    @SuppressWarnings("unchecked")
    public static List<String> getAll()
    {
        //@formatter:off
        return given()
        .expect()
             .statusCode(SC_OK)
        .when()
             .get(AlgorithmConstants.ROOT_URI)
        .then()
             .extract()
             .as(List.class);
        //@formatter:on
    }

    /**
     * Обновить данные алгоритма
     *
     * @param name      имя алгоритма
     * @param algorithm модель алгоритма
     */
    public static void update(String name, Algorithm algorithm)
    {
        //@formatter:off
        given()
            .pathParam(AlgorithmConstants.PATH_NAME, name)
            .contentType(ContentType.JSON)
            .body(algorithm.getProperties())
        .expect()
            .statusCode(SC_OK)
        .when()
            .put(AlgorithmConstants.ALGORITHM_URI);
        //@formatter:on
    }
}
