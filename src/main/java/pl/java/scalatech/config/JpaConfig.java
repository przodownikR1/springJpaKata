package pl.java.scalatech.config;

import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.hibernate5.HibernateExceptionTranslator;


@EntityScan(basePackages = "pl.java.scalatech.domain")
@EnableJpaRepositories(basePackages = "pl.java.scalatech.repository")
public class JpaConfig {

    @Bean
    public HibernateExceptionTranslator hibernateExceptionTranslator() {
        return new HibernateExceptionTranslator();
    }


}
