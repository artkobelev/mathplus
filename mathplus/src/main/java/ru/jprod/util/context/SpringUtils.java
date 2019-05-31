package ru.jprod.util.context;

import java.util.Map;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.springframework.beans.factory.BeanFactoryUtils;
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

    /**
     * Получить все бины определенного класса
     *
     * @param clazz класс
     * @param <T>
     * @return бины заданного класса
     */
    public <T> Map<String, T> getBeans(Class<T> clazz)
    {
        return BeanFactoryUtils.beansOfTypeIncludingAncestors(context, clazz);
    }

    @PostConstruct
    private void init()
    {
        instance = this;
    }
}