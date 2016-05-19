package pl.java.scalatech.filter;

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
               "CREATE TABLE IF NOT EXISTS Pupil ( id int , age int , pupilName VARCHAR (30), pupilNumber int )"
       },executionPhase=BEFORE_TEST_METHOD
)
@Sql(
        statements = {
                "insert into Pupil (id, age,pupilName,pupilNumber) values (1, 36, 'przodownik',1)",
                "insert into Pupil (id, age,pupilName,pupilNumber) values (2, 23, 'ola',2)",
                "insert into Pupil (id, age,pupilName,pupilNumber) values (3, 24, 'tola',3)",
                "insert into Pupil (id, age,pupilName,pupilNumber) values (4, 56, 'kalina',5)",
                "insert into Pupil (id, age,pupilName,pupilNumber) values (5, 21, 'bolek',6)",
                "insert into Pupil (id, age,pupilName,pupilNumber) values (6, 12, 'lolek',12)"
        },
        executionPhase = BEFORE_TEST_METHOD
)
@Sql(
        statements = {
                "delete from Pupil"
        },
        executionPhase = AFTER_TEST_METHOD
)
public @interface SqlPupilData {
}