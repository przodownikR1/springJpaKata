package pl.java.scalatech.spring;

import javax.sql.DataSource;

import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import net.ttddyy.dsproxy.support.ProxyDataSourceBuilder;

@Configuration
public class ProxyDsConfig {

    @Bean
    public JdbcTemplate jdbcTemplate(DataSource dataSource){
        return new JdbcTemplate(dataSource);
    }

    @Bean
    public DataSource actualDataSource() {
        return DataSourceBuilder
                .create(ProxyDsConfig.class.getClassLoader())
                .driverClassName("org.h2.Driver")
                .url("jdbc:h2:mem:testdb")  // in-memory db
                .username("sa")
                .password("")
                .build();
    }

    @Bean
    public DataSource dataSource(DataSource actualDataSource) {
        return ProxyDataSourceBuilder
                .create(actualDataSource)
                .name("MyDS")
                .logQueryToSysOut()
                .build();
    }
}
