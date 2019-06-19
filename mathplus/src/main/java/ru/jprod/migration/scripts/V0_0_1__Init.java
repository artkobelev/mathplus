package ru.jprod.migration.scripts;

import java.sql.Connection;

import org.flywaydb.core.Flyway;
import org.flywaydb.core.api.migration.Context;

import ru.jprod.migration.MigrationBaseScript;

/**
 * Пустая миграция. Исользуется для инициализации таблицы для {@link Flyway}
 *
 * @author artem
 * @since 22.05.2019
 */
public class V0_0_1__Init extends MigrationBaseScript
{
    @Override
    public void migrate(Context context, Connection connection) throws Exception
    {
    }
}
