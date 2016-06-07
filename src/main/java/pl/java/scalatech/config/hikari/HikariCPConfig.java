package pl.java.scalatech.config.hikari;

import java.util.Properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Getter;
import lombok.Setter;

@ConfigurationProperties( "spring" )
public class HikariCPConfig {
    @Getter @Setter
    private Properties hikariDatasource;



}