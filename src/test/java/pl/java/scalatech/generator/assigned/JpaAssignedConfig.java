package pl.java.scalatech.generator.assigned;

import org.springframework.boot.autoconfigure.PropertyPlaceholderAutoConfiguration;
import org.springframework.boot.autoconfigure.dao.PersistenceExceptionTranslationAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import pl.java.scalatech.domain.generator.impl.IdentifiableImpl;


@EntityScan(basePackages = "pl.java.scalatech.domain.generator")
@ComponentScan(basePackageClasses=IdentifiableImpl.class)
@EnableJpaRepositories(basePackages = "pl.java.scalatech.repository.generator")
@Import({ DataSourceAutoConfiguration.class, HibernateJpaAutoConfiguration.class, PersistenceExceptionTranslationAutoConfiguration.class,
    PropertyPlaceholderAutoConfiguration.class })
@Profile("assigned")
@Configuration

public class JpaAssignedConfig {

}
