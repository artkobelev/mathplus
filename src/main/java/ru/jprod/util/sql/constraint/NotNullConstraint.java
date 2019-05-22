package ru.jprod.util.sql.constraint;

public class NotNullConstraint implements Constraint
{
    private final static String SQL_PATTERN = "NOT NULL";

    @Override
    public String getName()
    {
        return "not null";
    }

    @Override
    public String getSql()
    {
        return SQL_PATTERN;
    }
}
