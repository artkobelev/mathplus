package ru.jprod.util.sql.dialect;

import org.hibernate.dialect.Dialect;
import org.hibernate.dialect.PostgresPlusDialect;

public class PostgresDDLDialect implements DDLDialect
{
    @Override
    public Dialect getHibernateDialect()
    {
        return new PostgresPlusDialect();
    }
}
