package pl.java.scalatech.config;

import org.springframework.boot.autoconfigure.PropertyPlaceholderAutoConfiguration;
import org.springframework.boot.autoconfigure.dao.PersistenceExceptionTranslationAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import pl.java.scalatech.config.hikari.HikariCPConfiguration;

@Configuration
@EnableJpaRepositories(basePackages = {"pl.java.scalatech.repository.mainPerson","pl.java.scalatech.repository.constructorResult"})


@EntityScan(basePackages = {"pl.java.scalatech.domain.mainPerson","pl.java.scalatech.domain.constructorResult"})
@PropertySource(value = "classpath:application-test-fast.properties")
@Import({ DataSourceAutoConfiguration.class, HibernateJpaAutoConfiguration.class, PersistenceExceptionTranslationAutoConfiguration.class,
        PropertyPlaceholderAutoConfiguration.class ,HikariCPConfiguration.class,JpaLoggerConfig.class})
public class TestJpaConfig {

}
