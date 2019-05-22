package ru.jprod.util.context;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component(SpringUtils.BEAN_NAME)
public class SpringUtils implements ApplicationContextAware
{
    public static final String BEAN_NAME = "springUtils";

    private static SpringUtils instance;

    @Inject
    private ApplicationContext context;

    public static SpringUtils get()
    {
        return instance;
    }

    public ApplicationContext getApplicationContext()
    {
        return context;
    }

    @Override
    public void setApplicationContext(ApplicationContext ac)
            throws BeansException
    {
        context = ac;
    }

    @PostConstruct
    private void init()
    {
        instance = this;
    }
}