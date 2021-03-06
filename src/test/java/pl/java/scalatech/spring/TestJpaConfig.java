package pl.java.scalatech.spring;
import org.springframework.boot.autoconfigure.PropertyPlaceholderAutoConfiguration;
import org.springframework.boot.autoconfigure.dao.PersistenceExceptionTranslationAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import pl.java.scalatech.config.JpaLoggerConfig;
import pl.java.scalatech.config.hikari.HikariCPConfiguration;

@Configuration
@ComponentScan(basePackages = { "pl.java.scalatech.repository" })
@EnableJpaRepositories(basePackages = "pl.java.scalatech.repository.mainPerson")
@EntityScan(basePackages = "pl.java.scalatech.domain.mainPerson")
@PropertySource(value = "classpath:application-test-fast.properties")
@Import({ DataSourceAutoConfiguration.class, HibernateJpaAutoConfiguration.class, PersistenceExceptionTranslationAutoConfiguration.class,
        PropertyPlaceholderAutoConfiguration.class ,JpaLoggerConfig.class,HikariCPConfiguration.class})
public class TestJpaConfig {

}
