package ru.jprod.util.sql;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ru.jprod.util.context.SpringUtils;
import ru.jprod.util.sql.column.ColumnDefinition;
import ru.jprod.util.sql.constraint.Constraint;
import ru.jprod.util.sql.constraint.NotNullConstraint;
import ru.jprod.util.sql.dialect.DDLDialect;
import ru.jprod.util.sql.dialect.H2DDLDialect;
import ru.jprod.util.sql.dialect.PostgresDDLDialect;

/**
 * Класс для получения и изменения объектаов базы данных посредством DDL
 *
 * @since 22.05.2019
 */
public class DDLTool
{
    private static final String ADD_COLUMN_SQL = "ALTER TABLE %s ADD %s %s";

    private static final DDLDialect DIALECT = SpringUtils.get().getApplicationContext()
            .getBean(DDLDialect.class);
    private static final Logger LOGGER = LoggerFactory.getLogger(DDLTool.class);

    private Connection connection;

    public DDLTool(Connection connection)
    {
        this.connection = connection;
    }

    /**
     * Проверить, что текущая БД H2
     *
     * @return true - БД H2, false - иначе
     */
    public static boolean isH2SQL()
    {
        return DIALECT instanceof H2DDLDialect;
    }

    /**
     * Проверить, что текущая БД Postgres
     *
     * @return true - БД Postgres, false - иначе
     */
    public static boolean isPostgres()
    {
        return DIALECT instanceof PostgresDDLDialect;
    }

    /**
     * Создать столбец в таблице
     *
     * @param table  имя таблицы
     * @param column модель столбца
     * @throws SQLException
     */
    public void createColumn(String table, ColumnDefinition column) throws SQLException
    {
        executeUpdate(String.format(ADD_COLUMN_SQL + createNotNullSqlOrEmpty(column), table, column.getName(),
                column.getTypeName(DIALECT.getHibernateDialect())));

        for (Constraint constraint : column.getConstraints())
        {
            if (!(constraint instanceof NotNullConstraint))
            {
                //add constraint
            }
        }
    }

    /**
     * Выполнить SQL скрипт
     *
     * @param sql скрипт
     */
    public void executeUpdate(String sql) throws SQLException
    {
        LOGGER.info("Start query: " + sql);
        try (Statement st = getConnection().createStatement())
        {
            st.executeUpdate(sql);
            LOGGER.info("\tDone query: " + sql);
        }
    }

    /**
     * Получить соединение
     *
     * @return соединение
     */
    public Connection getConnection()
    {
        return connection;
    }

    /**
     * Сгенерировать SQL скрипт для установки ограничения "NOT NULL" для столбца, если ограничение задано
     *
     * @param column модель столбца
     * @return SQL скрипт
     */
    private String createNotNullSqlOrEmpty(ColumnDefinition column)
    {
        Constraint notNullConstraint = column.getConstraints().stream().filter(e -> e instanceof NotNullConstraint)
                .findAny().orElse(null);
        return " " + (null == notNullConstraint ? "" : notNullConstraint.getSql());
    }
}
