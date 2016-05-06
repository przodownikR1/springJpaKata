package pl.java.scalatech.exercise.jpql;

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
                "INSERT INTO COMPANY (id, version, city,street, zipcode,name) VALUES (1, 0,'poznan','dolna','12345','touk')",
                "INSERT INTO COMPANY (id, version, city,street, zipcode,name) VALUES (2, 0,'warszawa','polna','55345','risco')",
                "INSERT INTO COMPANY (id, version, city,street, zipcode,name) VALUES (3, 0,'radom','koncowa','17345','javatech')",
                "INSERT INTO COMPANY (id, version, city,street, zipcode,name) VALUES (4, 0,'gdansk','skrajna','12909','scalatech')",
                "INSERT INTO COMPANY (id, version, city,street, zipcode,name) VALUES (5, 0,'wroclaw','tylnia','99776','commarch')",
                "INSERT INTO COMPANY (id, version, city,street, zipcode,name) VALUES (6, 0,'gdynia','przednia','56732','ibm')",


                "INSERT INTO Department (id, version,name) VALUES (1, 0,'java')",
                "INSERT INTO Department (id, version,name) VALUES (2, 0,'groovy')",
                "INSERT INTO Department (id, version,name) VALUES (3, 0,'hr')",
                "INSERT INTO Department (id, version,name) VALUES (4, 0,'uat')",
                "INSERT INTO Department (id, version,name) VALUES (5, 0,'qa')",
                "INSERT INTO Department (id, version,name) VALUES (6, 0,'social')",
                "INSERT INTO Department (id, version,name) VALUES (7, 0,'management')",


                "INSERT INTO Company_Department (Company_id, depts_id) VALUES (1, 2)",
                "INSERT INTO Company_Department (Company_id, depts_id) VALUES (1, 3)",
                "INSERT INTO Company_Department (Company_id, depts_id) VALUES (1, 4)",
                "INSERT INTO Company_Department (Company_id, depts_id) VALUES (2, 1)",
                "INSERT INTO Company_Department (Company_id, depts_id) VALUES (3, 2)",
                "INSERT INTO Company_Department (Company_id, depts_id) VALUES (4, 5)",
                "INSERT INTO Company_Department (Company_id, depts_id) VALUES (5, 6)",
                "INSERT INTO Company_Department (Company_id, depts_id) VALUES (6, 7)",
                "INSERT INTO Company_Department (Company_id, depts_id) VALUES (6, 1)",
                "INSERT INTO Company_Department (Company_id, depts_id) VALUES (6, 2)",

                "INSERT INTO Employee (id, version, age, firstName, lastName , pesel, salary,company_id) VALUES (1,0,34,'slawek','borowiec','57442342423',12342,1)",
                "INSERT INTO Employee (id, version, age, firstName, lastName , pesel, salary,company_id) VALUES (2,0,23,'pawel','kalicki','99742342423',4342,2)",
                "INSERT INTO Employee (id, version, age, firstName, lastName , pesel, salary,company_id) VALUES (3,0,45,'lukasz','nowak','58342423',10000,2)",
                "INSERT INTO Employee (id, version, age, firstName, lastName , pesel, salary,company_id) VALUES (4,0,65,'marek','kowal','787423',1200,3)",
                "INSERT INTO Employee (id, version, age, firstName, lastName , pesel, salary,company_id) VALUES (5,0,62,'jola','kwiatek','25645642342423',3434,4)",
                "INSERT INTO Employee (id, version, age, firstName, lastName , pesel, salary,company_id) VALUES (6,0,23,'ola','maciec','124342423',3442,5)",
                "INSERT INTO Employee (id, version, age, firstName, lastName , pesel, salary,company_id) VALUES (7,0,45,'kasia','gawel','111242423',3442,6)",
                "INSERT INTO Employee (id, version, age, firstName, lastName , pesel, salary,company_id) VALUES (8,0,55,'kalina','borsuk','22242342423',3442,3)",
                "INSERT INTO Employee (id, version, age, firstName, lastName , pesel, salary,company_id) VALUES (9,0,43,'maciek','lis','444342423',8862,1)",
                "INSERT INTO Employee (id, version, age, firstName, lastName , pesel, salary,company_id) VALUES (10,0,26,'pawel','wilk','7772423',4567,1)",
                "INSERT INTO Employee (id, version, age, firstName, lastName , pesel, salary,company_id) VALUES (11,0,35,'tomek','krawiec','77742423',4452,2)",
                "INSERT INTO Employee (id, version, age, firstName, lastName , pesel, salary,company_id) VALUES (12,0,36,'max','borowiecki','2345423',8002,4)",
                "INSERT INTO Employee (id, version, age, firstName, lastName , pesel, salary,company_id) VALUES (13,0,31,'jarek','mazowiecki','5654642342423',7772,5)",
                "INSERT INTO Employee (id, version, age, firstName, lastName , pesel, salary,company_id) VALUES (14,0,43,'slawek','pawlak','43542423',7342,4)",
                "INSERT INTO Employee (id, version, age, firstName, lastName , pesel, salary,company_id) VALUES (15,0,23,'sylwek','jackiewicz','6642342423',34542,3)",
                "INSERT INTO Employee (id, version, age, firstName, lastName , pesel, salary,company_id) VALUES (16,0,25,'piotr','wlodarczyk','223342423',5552,2)",









        },
        executionPhase = BEFORE_TEST_METHOD
)

public @interface SqlDataJPQL {
}