package pl.java.scalatech.exercise.inheritence.mapped;

import org.springframework.boot.autoconfigure.PropertyPlaceholderAutoConfiguration;
import org.springframework.boot.autoconfigure.dao.PersistenceExceptionTranslationAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import pl.java.scalatech.config.JpaLoggerConfig;


@EntityScan(basePackages = "pl.java.scalatech.domain.inheritence.mapped")
@EnableJpaRepositories(basePackages = "pl.java.scalatech.repository.inheritence.mapped")
@Import({ DataSourceAutoConfiguration.class, HibernateJpaAutoConfiguration.class, PersistenceExceptionTranslationAutoConfiguration.class,
    PropertyPlaceholderAutoConfiguration.class,JpaLoggerConfig.class })
@Profile(value={"mapped"})
@Configuration
public class MappedTableConfig {

}