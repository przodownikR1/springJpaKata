package pl.java.scalatech.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

import lombok.extern.slf4j.Slf4j;

@Configuration
@PropertySource("classpath:application.properties")
@Slf4j
public class PropertiesLoader {

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        log.info("+++  propertySource -> prop profile launch");
        return new PropertySourcesPlaceholderConfigurer();
    }


}