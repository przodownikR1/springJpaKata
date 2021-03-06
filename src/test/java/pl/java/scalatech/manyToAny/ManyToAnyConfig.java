package pl.java.scalatech.manyToAny;

import org.springframework.boot.autoconfigure.PropertyPlaceholderAutoConfiguration;
import org.springframework.boot.autoconfigure.dao.PersistenceExceptionTranslationAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.zaxxer.hikari.HikariConfig;

import pl.java.scalatech.config.JpaLoggerConfig;
import pl.java.scalatech.config.hikari.HikariCPConfig;
import pl.java.scalatech.config.hikari.HikariCPConfiguration;


@EntityScan(basePackages = "pl.java.scalatech.domain.manyToAny")
@EnableJpaRepositories(basePackages = "pl.java.scalatech.repository.manyToAny")
@Import({ DataSourceAutoConfiguration.class, HibernateJpaAutoConfiguration.class, PersistenceExceptionTranslationAutoConfiguration.class,
    PropertyPlaceholderAutoConfiguration.class,JpaLoggerConfig.class,HikariCPConfiguration.class })
@Profile({"manyToAny","logger"})
@Configuration

public class ManyToAnyConfig {

}
