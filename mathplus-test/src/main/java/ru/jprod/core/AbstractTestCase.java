package ru.jprod.core;

import org.apache.http.Consts;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import com.jayway.awaitility.Awaitility;
import com.jayway.awaitility.Duration;
import com.jayway.restassured.RestAssured;
import com.jayway.restassured.config.EncoderConfig;

import ru.jprod.cleaner.Cleaner;
import ru.jprod.core.config.Configuration;

/**
 * Базовая настройка перед запуском тестов
 *
 * @author artem
 * @since 25.05.19
 */
public abstract class AbstractTestCase
{
    private static final String baseURI;
    private static final Cleaner cleaner = Cleaner.get();
    private static final Configuration config = Configuration.get();

    static
    {
        // Настройка Rest Assured
        baseURI = "http://" + config.getAppHost() + ":" + config.getAppPort();
        RestAssured.baseURI = baseURI;
        RestAssured.config = RestAssured.config()
                .encoderConfig(new EncoderConfig(Consts.UTF_8.toString(), Consts.UTF_8.toString()));

        Awaitility.setDefaultPollInterval(Duration.ONE_SECOND);
    }

    /**
     * Действия выполняемые после завершения тестового класса.
     */
    @AfterAll
    public static void afterClass()
    {
        cleaner.clean(Cleaner.QueueMode.CLASS);
    }

    /**
     * Действия выполняемые перед выполнением тестового класса.
     */
    @BeforeAll
    public static void beforeClass()
    {
        cleaner.switchTo(Cleaner.QueueMode.CLASS);
    }

    /**
     * Действия выполняемые после завершения тестового метода.
     */
    @AfterEach
    public void after()
    {
        cleaner.clean(Cleaner.QueueMode.TEST);
    }

    /**
     * Действия выполняемые перед выполнением тестового метода.
     */
    @BeforeEach
    public void before()
    {
        cleaner.switchTo(Cleaner.QueueMode.TEST);
    }
}
