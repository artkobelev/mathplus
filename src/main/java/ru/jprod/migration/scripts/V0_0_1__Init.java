package ru.jprod.migration.scripts;

import java.sql.Connection;

import org.springframework.jdbc.core.JdbcTemplate;

import ru.jprod.migration.MigrationBaseScript;

public class V0_0_1__Init extends MigrationBaseScript
{
    @Override
    public void migrate(JdbcTemplate template, Connection connection) throws Exception
    {
    }
}
