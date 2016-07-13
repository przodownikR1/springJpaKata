package pl.java.scalatech.exercise.n1;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.runners.MethodSorters.NAME_ASCENDING;

import javax.persistence.EntityManager;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.slf4j.Slf4j;
import pl.java.scalatech.config.JpaLoggerConfig;
import pl.java.scalatech.config.PropertiesLoader;
import pl.java.scalatech.config.hikari.HikariCPConfiguration;
import pl.java.scalatech.domain.example.n1.JobCandidate;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { PropertiesLoader.class, JpaJN1Config.class,JpaLoggerConfig.class ,HikariCPConfiguration.class  })
@ActiveProfiles(value = {"n1","logger","dev"})
@Transactional
@Slf4j
@FixMethodOrder(NAME_ASCENDING)
public class SaveEmTest {

    @Autowired
    private EntityManager em;

    // tag::main[]
    @Test
    public void shouldCreateAndSaveProperty() {
        log.info("first");
        JobCandidate user = new JobCandidate(23l, "krolewski");
        em.persist(user);  

        JobCandidate loaded = em.createQuery("FROM JobCandidate c  WHERE c.fullName ='krolewski'", JobCandidate.class).getSingleResult();
        assertThat(loaded).isNotNull();
        assertThat(loaded.getAge()).isNull();
        assertThat(loaded.getFullName()).isNotNull().isEqualTo("krolewski");

        loaded.setAge(123); //<1>
        loaded = em.createQuery("FROM JobCandidate c  WHERE c.fullName ='krolewski'", JobCandidate.class).getSingleResult();
        assertThat(loaded.getAge()).isNotNull().isEqualTo(123);
        log.info("++++  {}", loaded);

    }
    // end::main[]

}
