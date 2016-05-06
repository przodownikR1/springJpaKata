package pl.java.scalatech.domain.fetching;

import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.BEFORE_TEST_METHOD;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.test.context.jdbc.Sql;
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
/*@Sql(
       statements = {

               "create table Address ( id bigint not null, version bigint, CITY varchar(255), PERSON_ID bigint, primary key (id))",
               "create table Person (   id bigint not null,version bigint,firstName varchar(255),lastName varchar(255),primary key (id))",
               " alter table Address add constraint FKpqc2o5roloc03ejedwnxpe6pg foreign key (PERSON_ID) references Person"


       },executionPhase=BEFORE_TEST_METHOD
)*/
@Sql(
        statements = {
                "insert into Person (id, version,firstName,lastName) values (1,0,'slawek1', 'borowiec1')",
                "insert into Person (id, version,firstName,lastName) values (2,0,'slawek2', 'borowiec2')",
                "insert into Person (id, version,firstName,lastName) values (3,0,'slawek3', 'borowiec3')",
                "insert into Person (id, version,firstName,lastName) values (4,0,'slawek4', 'borowiec4')",
                "insert into Person (id, version,firstName,lastName) values (5,0,'slawek5', 'borowiec5')",
                "insert into Person (id, version,firstName,lastName) values (6,0,'slawek6', 'borowiec6')",
                "insert into Person (id, version,firstName,lastName) values (7,0,'slawek7', 'borowiec7')",
                "insert into Person (id, version,firstName,lastName) values (8,0,'slawek8', 'borowiec8')",
                "insert into Person (id, version,firstName,lastName) values (9,0,'slawek9', 'borowiec9')",
                "insert into Person (id, version,firstName,lastName) values (10,0,'slawek10', 'borowiec10')",
                "insert into Person (id, version,firstName,lastName) values (11,0,'slawek11', 'borowiec11')",
                "insert into Person (id, version,firstName,lastName) values (12,0,'slawek12', 'borowiec12')",

                "insert into Address (id, version,CITY,PERSON_ID) values (1,0,'warszawa', 1)",
                "insert into Address (id, version,CITY,PERSON_ID) values (2,0,'poznan', 1)",
                "insert into Address (id, version,CITY,PERSON_ID) values (3,0,'lodz', 1)",
                "insert into Address (id, version,CITY,PERSON_ID) values (4,0,'warszawa', 2)",
                "insert into Address (id, version,CITY,PERSON_ID) values (5,0,'radom', 3)",
                "insert into Address (id, version,CITY,PERSON_ID) values (6,0,'krakow', 4)",
                "insert into Address (id, version,CITY,PERSON_ID) values (7,0,'wroclaw', 5)",
                "insert into Address (id, version,CITY,PERSON_ID) values (8,0,'gdansk', 6)",
                "insert into Address (id, version,CITY,PERSON_ID) values (9,0,'ilza', 7)",
                "insert into Address (id, version,CITY,PERSON_ID) values (10,0,'ciechanow', 8)",
                "insert into Address (id, version,CITY,PERSON_ID) values (11,0,'lezajsk', 9)",
                "insert into Address (id, version,CITY,PERSON_ID) values (12,0,'katowice', 10)",
                "insert into Address (id, version,CITY,PERSON_ID) values (13,0,'sopot', 11)",
                "insert into Address (id, version,CITY,PERSON_ID) values (14,0,'gdynia', 12)",
                "insert into Address (id, version,CITY,PERSON_ID) values (15,0,'poznan', 12)",



        },
        executionPhase = BEFORE_TEST_METHOD
)

public @interface SqlDataAccount {
}