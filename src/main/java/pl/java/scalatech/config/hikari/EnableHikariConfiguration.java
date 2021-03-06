package pl.java.scalatech.config.hikari;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.context.annotation.Import;

@Documented
@Inherited
@Target( ElementType.TYPE )
@Retention( RetentionPolicy.RUNTIME )
@Import( HikariCPConfiguration.class )
public @interface EnableHikariConfiguration {

}