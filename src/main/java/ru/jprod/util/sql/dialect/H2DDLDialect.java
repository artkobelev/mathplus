package ru.jprod.util.sql.dialect;

import org.hibernate.dialect.Dialect;
import org.hibernate.dialect.H2Dialect;

public class H2DDLDialect implements DDLDialect
{
    @Override
    public Dialect getHibernateDialect()
    {
        return new H2Dialect();
    }
}
