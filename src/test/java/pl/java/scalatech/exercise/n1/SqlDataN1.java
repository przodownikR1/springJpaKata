package pl.java.scalatech.exercise.n1;

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
                                       
                "INSERT INTO JobCandidate (id, version, fullName) VALUES (1, 0,'przodownik')",
                "INSERT INTO JobCandidate (id, version, fullName) VALUES (2, 0,'kowalksi')",
                "INSERT INTO JobCandidate (id, version, fullName) VALUES (3, 0,'nowak')",
                "INSERT INTO JobCandidate (id, version, fullName) VALUES (4, 0,'lis')",
                "INSERT INTO JobCandidate (id, version, fullName) VALUES (5, 0,'wilk')",
                "INSERT INTO JobCandidate (id, version, fullName) VALUES (6, 0,'sarna')",
                "INSERT INTO JobCandidate (id, version, fullName) VALUES (7, 0,'borsuk')",
                
                "INSERT INTO Skill (id, version, name,candidateId) VALUES (1, 0,'java',1)",   //przodownik
                "INSERT INTO Skill (id, version, name,candidateId) VALUES (2, 0,'hibernate',1)",//przodownik
                "INSERT INTO Skill (id, version, name,candidateId) VALUES (3, 0,'groovy',1)",//przodownik
                "INSERT INTO Skill (id, version, name,candidateId) VALUES (4, 0,'gradle',1)",//przodownik
                "INSERT INTO Skill (id, version, name,candidateId) VALUES (5, 0,'c#',2)", //kowalksi
                "INSERT INTO Skill (id, version, name,candidateId) VALUES (6, 0,'java',2)",//kowalksi
                "INSERT INTO Skill (id, version, name,candidateId) VALUES (7, 0,'maven',3)",//nowak
                "INSERT INTO Skill (id, version, name,candidateId) VALUES (8, 0,'c#',4)", //list
                "INSERT INTO Skill (id, version, name,candidateId) VALUES (9, 0,'c#',5)", //wilk
                "INSERT INTO Skill (id, version, name,candidateId) VALUES (10, 0,'c#',6)", //sarna
                "INSERT INTO Skill (id, version, name,candidateId) VALUES (11, 0,'eclipse',6)", //sarna
                "INSERT INTO Skill (id, version, name,candidateId) VALUES (12, 0,'uml',7)", //borsku
                
                
                 
        },
        executionPhase = BEFORE_TEST_METHOD
)

public @interface SqlDataN1 {
}