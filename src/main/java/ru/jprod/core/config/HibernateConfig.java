package ru.jprod.core.config;

import java.sql.SQLException;
import java.util.Properties;

import javax.annotation.PreDestroy;
import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.AvailableSettings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.LocalSessionFactoryBuilder;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@Configuration
public class HibernateConfig
{
    private static final Logger LOGGER = LoggerFactory.getLogger(HibernateConfig.class);
    /**
     * Перечень пакетов для сканирования entity классов
     */
    private static final String[] PACKAGES_TO_SCAN = { "ru.jprod.core.model" };
    @Value("${hibernate.cache.region.factory_class}")
    private String cacheRegionFactory;
    // Настройки подключения к БД
    @Value("${db.driver}")
    private String dbDriver;
    @Value("${db.password}")
    private String dbPasswd;
    @Value("${db.url}")
    private String dbURL;
    @Value("${db.user}")
    private String dbUser;
    // Настройки hibernate
    @Value("${hibernate.dialect}")
    private String hbDialect;
    @Value("${hibernate.format_sql}")
    private String hbFormatSql;
    @Value("${hibernate.hbm2ddl.auto}")
    private String hbHbm2ddl;
    @Value("${hibernate.id.new_generator_mappings}")
    private String hbNewGeneratorMappings;
    @Value("${hibernate.jdbc.lob.non_contextual_creation}")
    private String hbNonContextualCreation;
    @Value("${hibernate.show_sql}")
    private String hbShowSql;
    @Value("${hibernate.cache.use_second_level_cache}")
    private String hbUse2ndLevelCache;
    @Value("${hibernate.cache.use_query_cache}")
    private String hbUseQueryCache;
    @Value("${hibernate.use_sql_comments}")
    private String hbUseSqlComments;
    @Value("${db.generator.hikari_idleTimeout}")
    private int idleTimeout;
    @Value("${db.generator.hikari_leakDetectionThreshold}")
    private int leakDetectionThreshold;
    @Value("${db.generator.hikari_maxLifetime}")
    private int maxLifetime;
    @Value("${db.max_active_connections}")
    private int maxPoolSize;
    @Value("${db.generator.max_time_waiting_connection}")
    private Integer maxWaitTime;
    @Value("${db.generator.hikari_minimumIdle}")
    private int minIdle;

    @Bean(name = "dataSource", destroyMethod = "close")
    public DataSource getDataSource() throws SQLException
    {
        LOGGER.info("Connection URL: " + dbURL + ", driver: " + dbDriver);

        HikariConfig config = new HikariConfig();
        config.setDriverClassName(dbDriver);
        config.setUsername(dbUser);
        config.setPassword(dbPasswd);
        config.setJdbcUrl(dbURL);
        config.setMaximumPoolSize(maxPoolSize);
        config.setLeakDetectionThreshold(leakDetectionThreshold);
        config.setMinimumIdle(minIdle);
        config.setMaxLifetime(maxLifetime);
        config.setIdleTimeout(idleTimeout);
        config.setConnectionTimeout(maxWaitTime);

        config.validate();
        DataSource dataSource = new HikariDataSource(config);

        return dataSource;
    }

    @Bean(name = "sessionFactory")
    public SessionFactory getSessionFactory() throws SQLException
    {
        LocalSessionFactoryBuilder factory = new LocalSessionFactoryBuilder(getDataSource());
        factory.scanPackages(PACKAGES_TO_SCAN);
        Properties props = factory.getProperties();
        props.put(AvailableSettings.HBM2DDL_AUTO, hbHbm2ddl);
        props.put(AvailableSettings.DIALECT, hbDialect);
        props.put(AvailableSettings.SHOW_SQL, hbShowSql);
        props.put(AvailableSettings.FORMAT_SQL, hbFormatSql);
        props.put(AvailableSettings.USE_SQL_COMMENTS, hbUseSqlComments);
        props.put(AvailableSettings.USE_NEW_ID_GENERATOR_MAPPINGS, hbNewGeneratorMappings);
        props.put(AvailableSettings.USE_SECOND_LEVEL_CACHE, hbUse2ndLevelCache);
        props.put(AvailableSettings.USE_QUERY_CACHE, hbUseQueryCache);
        props.put(AvailableSettings.CACHE_REGION_FACTORY, cacheRegionFactory);
        props.put(AvailableSettings.NON_CONTEXTUAL_LOB_CREATION, hbNonContextualCreation);
        return factory.buildSessionFactory(new StandardServiceRegistryBuilder().applySettings(props).build());
    }

    @PreDestroy
    private void shutdown()
    {
        LOGGER.info("Application terminating correctly");
    }
}