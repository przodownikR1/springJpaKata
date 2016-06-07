package pl.java.scalatech.config.hikari;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;

import com.codahale.metrics.MetricRegistry;
import com.ryantenney.metrics.spring.config.annotation.EnableMetrics;
import com.zaxxer.hikari.HikariDataSource;

import lombok.extern.slf4j.Slf4j;

@Profile( "dev" )
@Configuration
@Slf4j
@EnableConfigurationProperties( HikariCPConfig.class )
@EnableMetrics(proxyTargetClass = true)
public class HikariCPConfiguration {

    @Autowired
    private MetricRegistry metricRegistry;

    @Autowired
    public HikariCPConfig hikariCPConfig;

    @Value( "${spring.datasource.url}" )
    public String url;

    @Value( "${spring.datasource.username}" )
    public String username;

    @Value( "${spring.datasource.password}" )
    public String password;

    @Value( "${spring.datasource.driver-class-name}" )
    public String driverClassName;

    @Bean(name="hikariDataSource",destroyMethod="close")
    @Primary
    public DataSource hikariDataSource() {
        log.info( "configure hikariCP config" );
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setDriverClassName( driverClassName );
      // dataSource.setDataSourceProperties( hikariCPConfig.getHikariDatasource() );
        dataSource.setUsername( username );
        dataSource.setPassword( password );
        dataSource.setJdbcUrl( url );
        dataSource.addDataSourceProperty("dataSource.cachePrepStmts", "true");
        dataSource.addDataSourceProperty("dataSource.prepStmtCacheSize", "250");
        dataSource.addDataSourceProperty("dataSource.prepStmtCacheSqlLimit", "2048");
        dataSource.addDataSourceProperty("dataSource.useServerPrepStmts", "true");
        dataSource.setMetricRegistry(metricRegistry);
        dataSource.setConnectionTestQuery("SELECT 1;");
        return dataSource;
    }

}