package ru.jprod.core.config;

import java.sql.SQLException;

import javax.inject.Inject;
import javax.sql.DataSource;

import org.flywaydb.core.Flyway;
import org.flywaydb.core.api.MigrationInfo;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import ru.jprod.util.context.SpringUtils;

/**
 * Настройки бина миграций {@link Flyway}
 *
 * @author artem
 * @since 21.05.2019
 */
@Configuration
public class FlyWayConfig
{
    public static final String BEAN_NAME = "flyway";
    /**
     * Перечень пакетов для для поиска миграций FlyWay
     */
    private static final String FLYWAY_LOCATIONS = "classpath:ru/jprod/migration/scripts";
    @Inject
    private DataSource dataSource;

    /**
     * Сервис для запуска миграций
     *
     * @return FlyWay
     * @throws SQLException
     */
    @Bean(name = BEAN_NAME)
    @DependsOn(SpringUtils.BEAN_NAME)
    public Flyway getFlyWay() throws SQLException
    {
        Flyway flyway = new Flyway();
        flyway.setDataSource(dataSource);
        flyway.setLocations(FLYWAY_LOCATIONS);
        if (isEmptyMigration(flyway))
        {
            // Пропускаем все миграции на пустой базе
            MigrationInfo[] pending = flyway.info().pending();
            flyway.setBaselineVersion(pending[pending.length - 1].getVersion());
            flyway.baseline();
        }
        else
        {
            flyway.migrate();
        }
        return flyway;
    }

    /**
     * Проверить наличие миграций в БД
     *
     * @param flyway объект манипулирования миграциями
     * @return true - миграции выполнялись, false - иначе
     */
    private boolean isEmptyMigration(Flyway flyway)
    {
        return null == flyway.info().current();
    }
}
