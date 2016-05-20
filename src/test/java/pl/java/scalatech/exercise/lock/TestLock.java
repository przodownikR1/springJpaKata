package pl.java.scalatech.exercise.lock;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.LockModeType;

import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lombok.extern.slf4j.Slf4j;
import pl.java.scalatech.config.JpaLoggerConfig;
import pl.java.scalatech.config.PropertiesLoader;
import pl.java.scalatech.domain.mainPerson.Person;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { PropertiesLoader.class, PersonJpaConfig.class, JpaLoggerConfig.class })
@ActiveProfiles(value = { "logger" })
@Slf4j
public class TestLock {

    @Autowired
    private EntityManagerFactory emf;

    @org.junit.Test
    @Ignore
    public void shouldLock() {
        Person person = Person.builder().email("przodownik@gmail.com").firstname("slawek").build();

        // Version number of record 1 gets increamented by 1
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(person);
        Person loaded = em.find(Person.class, 1l);
        loaded.setFirstname(loaded.getFirstname() + "_!");
        em.getTransaction().commit();
        em.close();
        // Version number of record 1 gets increamented by 1
        // for LockModeType.OPTIMISTIC_FORCE_INCREMENT read
        EntityManager em2 = emf.createEntityManager();
        em2.getTransaction().begin();
        loaded = em2.find(Person.class, 1l, LockModeType.OPTIMISTIC_FORCE_INCREMENT);
        em2.getTransaction().commit();
               em2.close();
        emf.close();

    }
}
