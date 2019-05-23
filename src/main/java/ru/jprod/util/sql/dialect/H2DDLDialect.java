package ru.jprod.util.sql.dialect;

import org.hibernate.dialect.Dialect;
import org.hibernate.dialect.H2Dialect;

/**
 * Диалект БД H2
 *
 * @author akobelev
 * @since 23.05.2019
 */
public class H2DDLDialect implements DDLDialect
{
    @Override
    public Dialect getHibernateDialect()
    {
        return new H2Dialect();
    }
}
