package pl.java.scalatech.config.h2;

import java.sql.SQLException;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.annotation.Order;
import org.springframework.orm.jpa.vendor.Database;

import com.zaxxer.hikari.HikariConfig;

import lombok.extern.slf4j.Slf4j;

@Configuration
@Slf4j
@Profile(value = "h2-test")
@Order(10001)
public class JpaEmbeddedConfig extends JpaConfig {


    @Override
    public Database dataBase() {
        return Database.H2;
    }

    @Override
    public void dataSourceConfigure(HikariConfig config) throws SQLException {
        config.setDataSourceClassName("org.h2.jdbcx.JdbcDataSource");
        config.setConnectionTestQuery("VALUES 1");
        config.addDataSourceProperty("URL", "jdbc:h2:~/test");
        config.addDataSourceProperty("user", "sa");
        config.addDataSourceProperty("password", "");


    }

}
