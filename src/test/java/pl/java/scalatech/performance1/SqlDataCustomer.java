package pl.java.scalatech.performance1;

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
                "insert into Location (locationId, version, name) values (1,0,'warszawa')",
                "insert into Location (locationId, version, name) values (2,0,'radom')",
                "insert into Location (locationId, version, name) values (3,0,'kielce')",
                "insert into Location (locationId, version, name) values (4,0,'wroclaw')",

                "insert into Customer (customerId, version, name,locationId) values (1,0,'przodownik',1)",
                "insert into Customer (customerId, version, name,locationId) values (2,0,'mike',2)",
                "insert into Customer (customerId, version, name,locationId) values (3,0,'roy',3)",
                "insert into Customer (customerId, version, name,locationId) values (4,0,'money',4)",
                "insert into Customer (customerId, version, name,locationId) values (5,0,'ggg',1)",
                "insert into Customer (customerId, version, name,locationId) values (6,0,'tyson',1)",

                "insert into Job (jobId, version, name,customerId) values (1,0,'programmer',1)",
                "insert into Job (jobId, version, name,customerId) values (2,0,'electric',1)",
                "insert into Job (jobId, version, name,customerId) values (3,0,'mechanic',1)",
                "insert into Job (jobId, version, name,customerId) values (4,0,'hairCutter',1)",
                "insert into Job (jobId, version, name,customerId) values (5,0,'writer',2)",
                "insert into Job (jobId, version, name,customerId) values (6,0,'developer',3)",
                "insert into Job (jobId, version, name,customerId) values (7,0,'tester',4)",
                "insert into Job (jobId, version, name,customerId) values (8,0,'cooker',5)",
                "insert into Job (jobId, version, name,customerId) values (9,0,'clerk',5)",







        },
        executionPhase = BEFORE_TEST_METHOD
)

public @interface SqlDataCustomer {
}