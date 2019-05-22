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

    public static boolean isH2SQL()
    {
        return DIALECT instanceof H2DDLDialect;
    }

    public static boolean isPostgres()
    {
        return DIALECT instanceof PostgresDDLDialect;
    }

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

    public int executeUpdate(String sql) throws SQLException
    {
        LOGGER.info("Start query: " + sql);
        try (Statement st = getConnection().createStatement())
        {
            int count = st.executeUpdate(sql);
            LOGGER.info("\tDone query: " + sql);
            return count;
        }
    }

    public Connection getConnection()
    {
        return connection;
    }

    private String createNotNullSqlOrEmpty(ColumnDefinition column)
    {
        Constraint notNullConstraint = column.getConstraints().stream().filter(e -> e instanceof NotNullConstraint)
                .findAny().orElse(null);
        return " " + (null == notNullConstraint ? "" : notNullConstraint.getSql());
    }
}
