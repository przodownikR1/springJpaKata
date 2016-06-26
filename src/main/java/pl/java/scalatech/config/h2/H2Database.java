package pl.java.scalatech.config.h2;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.h2.tools.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Profile;
import org.springframework.core.annotation.Order;
import org.springframework.orm.jpa.vendor.Database;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@Configuration
@Profile(value="h2")
@Order(10001)
public class H2Database extends JpaConfig{
   
    @Bean(destroyMethod = "close")
    @DependsOn(value = "h2Server")
    DataSource dataSource(Server h2Server) throws SQLException {
        HikariConfig hikariConfig = new HikariConfig();
        dataSourceConfigure(hikariConfig);
        hikariConfig.setMaximumPoolSize(maxPoolSize);
        hikariConfig.setConnectionTimeout(connectionTimeoutMs);
        hikariConfig.setIdleTimeout(idleTimeoutMs);
        hikariConfig.setMaxLifetime(maxLifetimeMs);
        hikariConfig.setRegisterMbeans(registerMbeans);
        hikariConfig.setConnectionTestQuery("VALUES 1");
        hikariConfig.addDataSourceProperty("useServerPrepStmts", username);
        HikariDataSource dataSource = new HikariDataSource(hikariConfig);
        
        createTcpServer();
       // CodaHaleMetricsTracker cmt = new CodaHaleMetricsTracker(pool, dataSource.getMetricRegistry());
        return dataSource;
        
    }

    @Bean(name = "h2Server", initMethod = "start", destroyMethod = "stop")
    @DependsOn(value = "h2WebServer")
    public org.h2.tools.Server createTcpServer() throws SQLException {
        return org.h2.tools.Server.createTcpServer("-tcp,-tcpAllowOthers,-tcpPort,9092".split(","));
    }

    @Bean(name = "h2WebServer", initMethod = "start", destroyMethod = "stop")
    public org.h2.tools.Server createWebServer() throws SQLException {
        return org.h2.tools.Server.createWebServer("-web,-webAllowOthers,-webPort,8082".split(","));
    }

   
    @Override
    public Database dataBase() {
        return Database.H2;
    }

    @Override
    public void dataSourceConfigure(HikariConfig hikariConfig) throws SQLException {
        hikariConfig.addDataSourceProperty("url", url);
        hikariConfig.setUsername(username);
        hikariConfig.setPassword(password);
        hikariConfig.setDataSourceClassName(driver);
    }
}
