package pl.java.scalatech.exercise.one;

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

import pl.java.scalatech.repository.exerciseOne.SampleRepo;


@EntityScan(basePackages = "pl.java.scalatech.domain.exerciseOne")
@EnableJpaRepositories(basePackages = "pl.java.scalatech.repository.exerciseOne")
@ComponentScan(basePackageClasses=SampleRepo.class)
@Import({ DataSourceAutoConfiguration.class, HibernateJpaAutoConfiguration.class, PersistenceExceptionTranslationAutoConfiguration.class,
    PropertyPlaceholderAutoConfiguration.class })
@Profile("exerciseOne")
@Configuration
public class Jpa1Config {

}
