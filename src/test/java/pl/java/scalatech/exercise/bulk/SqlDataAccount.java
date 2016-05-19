package pl.java.scalatech.exercise.bulk;

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

               "create table Bulk_Address ( id bigint not null, version bigint, CITY varchar(255), PERSON_ID bigint, primary key (id))",
               "create table Person (   id bigint not null,version bigint,firstName varchar(255),lastName varchar(255),primary key (id))",
               " alter table Bulk_Address add constraint FKpqc2o5roloc03ejedwnxpe6pg foreign key (PERSON_ID) references Person"


       },executionPhase=BEFORE_TEST_METHOD
)*/
@Sql(
        statements = {


                "insert into Bulk_Address (id, version,CITY) values (1,0,'warszawa')",
                "insert into Bulk_Address (id, version,CITY) values (2,0,'poznan')",
                "insert into Bulk_Address (id, version,CITY) values (3,0,'lodz')",
                "insert into Bulk_Address (id, version,CITY) values (4,0,'warszawa')",
                "insert into Bulk_Address (id, version,CITY) values (5,0,'radom')",
                "insert into Bulk_Address (id, version,CITY) values (6,0,'krakow')",
                "insert into Bulk_Address (id, version,CITY) values (7,0,'wroclaw')",
                "insert into Bulk_Address (id, version,CITY) values (8,0,'gdansk')",
                "insert into Bulk_Address (id, version,CITY) values (9,0,'ilza')",
                "insert into Bulk_Address (id, version,CITY) values (10,0,'ciechanow')",
                "insert into Bulk_Address (id, version,CITY) values (11,0,'lezajsk')",
                "insert into Bulk_Address (id, version,CITY) values (12,0,'katowice')",
                "insert into Bulk_Address (id, version,CITY) values (13,0,'sopot')",
                "insert into Bulk_Address (id, version,CITY) values (14,0,'gdynia')",
                "insert into Bulk_Address (id, version,CITY) values (15,0,'poznan')",
                "insert into Bulk_Address (id, version,CITY) values (16,0,'warszawa')",
                "insert into Bulk_Address (id, version,CITY) values (17,0,'poznan')",
                "insert into Bulk_Address (id, version,CITY) values (18,0,'lodz')",
                "insert into Bulk_Address (id, version,CITY) values (19,0,'warszawa')",
                "insert into Bulk_Address (id, version,CITY) values (20,0,'radom')",
                "insert into Bulk_Address (id, version,CITY) values (21,0,'krakow')",
                "insert into Bulk_Address (id, version,CITY) values (22,0,'wroclaw')",
                "insert into Bulk_Address (id, version,CITY) values (23,0,'gdansk')",
                "insert into Bulk_Address (id, version,CITY) values (24,0,'ilza')",
                "insert into Bulk_Address (id, version,CITY) values (25,0,'ciechanow')",
                "insert into Bulk_Address (id, version,CITY) values (26,0,'lezajsk')",
                "insert into Bulk_Address (id, version,CITY) values (27,0,'katowice')",
                "insert into Bulk_Address (id, version,CITY) values (28,0,'sopot')",
                "insert into Bulk_Address (id, version,CITY) values (29,0,'gdynia')",
                "insert into Bulk_Address (id, version,CITY) values (30,0,'poznan')"



        },
        executionPhase = BEFORE_TEST_METHOD
)

public @interface SqlDataAccount {
}