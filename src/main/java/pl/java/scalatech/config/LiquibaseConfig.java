package pl.java.scalatech.config;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import liquibase.integration.spring.SpringLiquibase;
import lombok.extern.slf4j.Slf4j;
@Slf4j
@Profile("liquibase")
@Configuration
public class LiquibaseConfig {
    @Bean
    public SpringLiquibase liquibase(DataSource datasource) {
        log.info("Configuring Liquibase");
        SpringLiquibase liquibase = new SpringLiquibase();
        liquibase.setDataSource(datasource);       
        liquibase.setChangeLog("classpath:/db/changelogs.xml");
 //     liquibase.setContexts("development, production");
        return liquibase;
    }
    
 
    
}
