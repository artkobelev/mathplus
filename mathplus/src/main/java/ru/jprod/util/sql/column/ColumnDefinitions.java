package ru.jprod.util.sql.column;

import java.sql.Types;

public class ColumnDefinitions
{
    public static ColumnDefinition longVarChar(String name)
    {
        return new ColumnDefinition(name, Types.LONGVARCHAR);
    }
}
