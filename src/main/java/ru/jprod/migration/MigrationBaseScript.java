package ru.jprod.migration;

import java.sql.Connection;

import org.flywaydb.core.api.migration.spring.SpringJdbcMigration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;

public abstract class MigrationBaseScript implements SpringJdbcMigration
{
    private static final Logger LOGGER = LoggerFactory.getLogger(MigrationBaseScript.class);

    @Override
    public void migrate(JdbcTemplate jdbcTemplate) throws Exception
    {
        LOGGER.info("Starting migration " + this.getClass().getName());
        migrate(jdbcTemplate, jdbcTemplate.getDataSource().getConnection());
    }

    /**
     * Выполнение команд миграции
     *
     * @param template   {@link JdbcTemplate}
     * @param connection подключение к базе
     * @throws Exception
     */
    public abstract void migrate(JdbcTemplate template, Connection connection) throws Exception;
}
