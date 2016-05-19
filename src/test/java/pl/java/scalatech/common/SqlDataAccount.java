package pl.java.scalatech.common;

import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.AFTER_TEST_METHOD;
import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.BEFORE_TEST_METHOD;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.test.context.jdbc.Sql;
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Sql(
       statements = {
               "CREATE TABLE IF NOT EXISTS SimpleMessages ( id int , MESSAGE_TEXT VARCHAR (1000) )"
       },executionPhase=BEFORE_TEST_METHOD
)
@Sql(
        statements = {
                "insert into SimpleMessages (id, MESSAGE_TEXT) values (1, 'hello slawek')",
                "insert into SimpleMessages (id, MESSAGE_TEXT) values (2, 'hello world')",
                "insert into SimpleMessages (id, MESSAGE_TEXT) values (3, 'hello przodownik')"
        },
        executionPhase = BEFORE_TEST_METHOD
)
@Sql(
        statements = {
                "delete from SimpleMessages"
        },
        executionPhase = AFTER_TEST_METHOD
)
public @interface SqlDataAccount {
}