package ru.jprod.migration;

import java.sql.Connection;

import org.flywaydb.core.api.migration.BaseJavaMigration;
import org.flywaydb.core.api.migration.Context;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Основной класс для миграций.
 *
 * @author artem
 * @since 22.05.2019
 */
public abstract class MigrationBaseScript extends BaseJavaMigration
{
    private static final Logger LOGGER = LoggerFactory.getLogger(MigrationBaseScript.class);

    @Override
    public void migrate(Context context) throws Exception
    {
        LOGGER.info("Starting migration " + this.getClass().getName());
        migrate(context, context.getConnection());
    }

    /**
     * Выполнение команд миграции
     *
     * @param context   {@link Context}
     * @param connection подключение к базе
     * @throws Exception
     */
    public abstract void migrate(Context context, Connection connection) throws Exception;
}
