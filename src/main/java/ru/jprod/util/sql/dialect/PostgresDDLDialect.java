package ru.jprod.util.sql.dialect;

import org.hibernate.dialect.Dialect;
import org.hibernate.dialect.PostgresPlusDialect;

/**
 * Диалект БД Postgres
 *
 * @author akobelev
 * @since 23.05.2019
 */
public class PostgresDDLDialect implements DDLDialect
{
    @Override
    public Dialect getHibernateDialect()
    {
        return new PostgresPlusDialect();
    }
}
