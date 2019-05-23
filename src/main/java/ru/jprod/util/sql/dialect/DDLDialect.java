package ru.jprod.util.sql.dialect;

import org.hibernate.dialect.Dialect;

/**
 * Интерфейс содержит методы для работы с диалектом
 *
 * @author artem
 * @since 23.05.2019
 */
public interface DDLDialect
{
    /**
     * @return диалект из hibernate, используется для манипуляции со столбцами
     * (hibernate диалект знает как преобразовывать sql типы данных в sql скрипты работы с колонками - создание)
     */
    Dialect getHibernateDialect();
}
