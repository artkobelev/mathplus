package ru.jprod.migration.scripts;

import java.sql.Connection;

import org.flywaydb.core.api.migration.Context;

import ru.jprod.core.model.Algorithm;
import ru.jprod.migration.MigrationBaseScript;
import ru.jprod.util.sql.DDLTool;

/**
 * Добавление к таблице {@link Algorithm} столбца 'script' с ограничением NOT NULL
 *
 * @author artem
 * @since 22.05.2019
 */
public class V0_1_1__Add_column_script_to_algorithm extends MigrationBaseScript
{
    @Override
    public void migrate(Context context, Connection connection) throws Exception
    {
        String table = "tbl_algorithm";
        String columnName = "script";
        DDLTool ddlTool = new DDLTool(connection);

        ddlTool.executeUpdate(String.format("ALTER TABLE %s ADD %s %s NOT NULL;", table, columnName, "TEXT"));
    }
}
