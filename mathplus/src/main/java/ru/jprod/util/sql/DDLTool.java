package ru.jprod.util.sql;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Класс для получения и изменения объектаов базы данных посредством DDL
 *
 * @since 22.05.2019
 */
public class DDLTool
{
    private static final Logger LOGGER = LoggerFactory.getLogger(DDLTool.class);

    private Connection connection;

    public DDLTool(Connection connection)
    {
        this.connection = connection;
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
}
