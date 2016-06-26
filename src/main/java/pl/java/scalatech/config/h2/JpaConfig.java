package pl.java.scalatech.config.h2;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaDialect;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import lombok.extern.slf4j.Slf4j;
import net.sf.log4jdbc.tools.Log4JdbcCustomFormatter;
import net.sf.log4jdbc.tools.LoggingType;
import pl.java.scalatech.jpa.CustomHibernateJpaDialect;
@Profile(value="h2")
@EnableJpaRepositories(basePackages = "pl.java.scalatech.repository")
@PropertySource("classpath:spring-data.properties")
@Slf4j
public abstract class JpaConfig {


    @Autowired
    private Environment env;

    @Value("${dataSource.driverClassName}")
    protected String driver;

    @Value("${dataSource.url}")
    protected String url;

    @Value("${dataSource.username}")
    protected String username;

    @Value("${dataSource.password}")
    protected String password;

    @Value("${hibernate.dialect}")
    protected String dialect;

    @Value("${hibernate.hbm2ddl.auto}")
    protected Boolean hbm2ddlAuto;

    @Value("${hibernate.show.sql}")
    protected Boolean showSql;

    @Value("${jpa.package}")
    protected String jpaPackage;

    @Value("${jpa.hikariMaxPoolSize}")
    protected int maxPoolSize;

    @Value("${jpa.hikariConnectionTimeoutMs}")
    protected long connectionTimeoutMs;

    @Value("${jpa.hikariIdleTimeoutMs}")
    protected long idleTimeoutMs;

    @Value("${jpa.hikariMaxLifetimeMs}")
    protected long maxLifetimeMs;

    @Value("${jpa.hikariRegisterMbeans}")
    protected boolean registerMbeans;



    public abstract void dataSourceConfigure(HikariConfig hikariConfig) throws SQLException;
    public abstract Database dataBase();



    @Bean
    public DataSource datasource() throws SQLException{
        HikariConfig config = new HikariConfig();
        dataSourceConfigure(config);
        config.setMaximumPoolSize(maxPoolSize);
       // config.setConnectionTimeout(connectionTimeoutMs);
        config.setIdleTimeout(idleTimeoutMs);
        config.setMaxLifetime(maxLifetimeMs);
        config.setRegisterMbeans(registerMbeans);
        config.setPoolName("pool");

        HikariDataSource dataSource = new HikariDataSource(config);
        return dataSource;
}

    @Bean
    public PlatformTransactionManager transactionManager() {
        return new JpaTransactionManager();
    }

    @Bean
    public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
        return new PersistenceExceptionTranslationPostProcessor();
    }

    public Map<String, Object> jpaProperties() {
        Map<String, Object> props = new HashMap<>();
        /*
         * props.put("hibernate.cache.use_query_cache", "true");
         * props.put("hibernate.cache.region.factory_class", "org.hibernate.cache.ehcache.EhCacheRegionFactory");
         * props.put("hibernate.cache.provider_class", "org.hibernate.cache.ehcache.EhCacheRegionFactory");
         * props.put("hibernate.cache.use_second_level_cache", "true");
         */
        return props;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() throws SQLException {
        log.info("+++ entityManagerFactory started ...");
        LocalContainerEntityManagerFactoryBean lef = new LocalContainerEntityManagerFactoryBean();
        lef.setJpaDialect(customJpaDialect());
        lef.setDataSource(datasource());
        lef.setJpaVendorAdapter(jpaVendorAdapter());
        lef.setJpaPropertyMap(jpaProperties());
        lef.setPackagesToScan(jpaPackage); // eliminate persistence.xml
        return lef;
    }

    @Bean
    public JpaVendorAdapter jpaVendorAdapter() {
        HibernateJpaVendorAdapter hibernateJpaVendorAdapter = new HibernateJpaVendorAdapter();
        hibernateJpaVendorAdapter.setShowSql(showSql);
        hibernateJpaVendorAdapter.setGenerateDdl(hbm2ddlAuto);
        hibernateJpaVendorAdapter.setDatabase(dataBase());
        hibernateJpaVendorAdapter.setDatabasePlatform(dialect);
        return hibernateJpaVendorAdapter;
    }

    @Bean
    public Log4JdbcCustomFormatter logFormater() {
        Log4JdbcCustomFormatter formatter = new Log4JdbcCustomFormatter();
        formatter.setLoggingType(LoggingType.SINGLE_LINE);
        formatter.setSqlPrefix("SQL:\r");
        return formatter;
    }

    public JpaDialect customJpaDialect() {
        return new CustomHibernateJpaDialect();
    }

}
