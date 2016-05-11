package pl.java.scalatech.config;

import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@EntityScan(basePackages = "pl.java.scalatech.domain")
@EnableJpaRepositories(basePackages = "pl.java.scalatech.repository")
public class JpaConfig {



}
