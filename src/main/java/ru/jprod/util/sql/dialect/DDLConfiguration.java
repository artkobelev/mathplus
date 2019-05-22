package ru.jprod.util.sql.dialect;

import java.sql.Connection;
import java.sql.SQLException;

import javax.inject.Inject;
import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import ru.jprod.util.exceptions.MathplusException;

@Configuration
public class DDLConfiguration
{
    public static final String H2_DBNAME = "H2";
    public static final String POSTGRESQL_DBNAME = "PostgreSQL";
    //    public static final String ORACLE_DBNAME = "Oracle";
    //    public static final String SQL_SERVER_DBNAME = "Microsoft SQL Server";

    @Inject
    private DataSource dataSource;

    @Bean(name = "ddlDialect")
    public DDLDialect getDialect() throws SQLException
    {
        try (Connection connection = dataSource.getConnection())
        {
            String dbName = connection.getMetaData().getDatabaseProductName();
            if (POSTGRESQL_DBNAME.equals(dbName))
            {
                return new PostgresDDLDialect();
            }
            //            else if (ORACLE_DBNAME.equals(dbName))
            //            {
            //                return new OracleDDLDialect();
            //            }
            //            else if (SQL_SERVER_DBNAME.equals(dbName))
            //            {
            //                return new MSDDLDialect();
            //            }
            else if (H2_DBNAME.equals(dbName))
            {
                return new H2DDLDialect();
            }
            else
            {
                throw new MathplusException("Unknown db: " + dbName);
            }
        }
    }
}
