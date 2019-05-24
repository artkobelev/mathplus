package ru.jprod.migration.scripts;

import java.sql.Connection;

import org.springframework.jdbc.core.JdbcTemplate;

import ru.jprod.core.model.Algorithm;
import ru.jprod.migration.MigrationBaseScript;
import ru.jprod.util.sql.DDLTool;
import ru.jprod.util.sql.column.ColumnDefinition;
import ru.jprod.util.sql.column.ColumnDefinitions;
import ru.jprod.util.sql.constraint.NotNullConstraint;

/**
 * Добавление к таблице {@link Algorithm} столбца 'script' с ограничением NOT NULL
 *
 * @author artem
 * @since 22.05.2019
 */
public class V0_1_1__Add_column_script_to_algorithm extends MigrationBaseScript
{
    @Override
    public void migrate(JdbcTemplate template, Connection connection) throws Exception
    {
        String table = "tbl_algorithm";
        String columnName = "script";
        DDLTool ddlTool = new DDLTool(connection);

        ColumnDefinition column = ColumnDefinitions.longVarChar(columnName);
        column.addConstraint(new NotNullConstraint());
        ddlTool.createColumn(table, column);
    }
}
