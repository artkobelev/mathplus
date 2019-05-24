package ru.jprod.core.model.dao;

import javax.inject.Inject;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 * Общаяя логика DAO.
 *
 * @author artem
 * @since 20.05.2019
 */
public abstract class AbstractDAO
{
    @Inject
    private SessionFactory sessionFactory;

    /**
     * Получить текущую сессию
     *
     * @return {@link Session}
     */
    protected Session session()
    {
        return sessionFactory.getCurrentSession();
    }
}
