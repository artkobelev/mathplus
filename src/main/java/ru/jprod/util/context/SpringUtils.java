package ru.jprod.util.context;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

/**
 * Вспомогательные методы для работы с spring
 *
 * @author akobelev
 * @since 20.05.2019
 */
@Component(SpringUtils.BEAN_NAME)
public class SpringUtils
{
    public static final String BEAN_NAME = "springUtils";

    /**
     * Инстанс класса
     */
    private static SpringUtils instance;

    @Inject
    private ApplicationContext context;

    public static SpringUtils get()
    {
        return instance;
    }

    /**
     * Получить контекст приложения
     *
     * @return контекст
     */
    public ApplicationContext getApplicationContext()
    {
        return context;
    }

    @PostConstruct
    private void init()
    {
        instance = this;
    }
}