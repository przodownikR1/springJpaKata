package pl.java.scalatech.spring;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.PropertyPlaceholderAutoConfiguration;
import org.springframework.boot.autoconfigure.dao.PersistenceExceptionTranslationAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource(value = "classpath:application-test-fast.properties")
@Import({ DataSourceAutoConfiguration.class, PersistenceExceptionTranslationAutoConfiguration.class,PropertyPlaceholderAutoConfiguration.class })
public class TestJSimpleConfig {


    @Autowired
    private DataSource orginalDs;
/*
    public DataSource dataSource(@Qualifier("orginalDs")DataSource orginalDs){
            return ProxyDataSourceBuilder
                .create(orginalDs)
                .name("MyDS")
                .logQueryToSysOut()    // logQueryBySlf4j(), logQueryToSysOut()
                .countQuery()
                .build();
    }*/

}
