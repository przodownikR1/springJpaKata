package pl.java.scalatech.exercise.save;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.LockModeType;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lombok.extern.slf4j.Slf4j;
import pl.java.scalatech.config.PropertiesLoader;
import pl.java.scalatech.domain.person.Person;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { PropertiesLoader.class, JpaSaveConfig.class })
//@Transactional
@ActiveProfiles(value={"save","logger"})
@Slf4j
public class SaveTest {

    @Autowired
    private EntityManagerFactory emf;
  

    @Test
    public void shouldFilterWork(){
        
        EntityManager entityManager1 = emf.createEntityManager();
        entityManager1.getTransaction().begin();
        entityManager1.persist(Person.builder().email("przodownikR1_1@gmail.com").firstname("slawek1").disable(true).build());
        entityManager1.getTransaction().commit();
        log.info("{}",entityManager1.createQuery("FROM Person").getResultList());
        EntityManager entityManager = emf.createEntityManager();        
        Person loaded = entityManager.createQuery("from Person", Person.class).getSingleResult();
        loaded.setDisable(false);
        entityManager = emf.createEntityManager();
        Person loaded1 = entityManager.find(Person.class, 1l);
        log.info("{}",loaded1);
        
    }
    @Test
    public void shouldLockReadWork(){
        EntityManager entityManager1 = emf.createEntityManager();
        entityManager1.getTransaction().begin();
        entityManager1.persist(Person.builder().email("przodownikR1_1@gmail.com").firstname("slawek1").disable(true).build());
        entityManager1.getTransaction().commit();
        log.info("{}",entityManager1.createQuery("FROM Person").getResultList());
        EntityManager entityManager = emf.createEntityManager();
        entityManager.getTransaction().begin();
        Person loaded1 = entityManager.find(Person.class, 1l,LockModeType.PESSIMISTIC_READ);
        log.info("{}",loaded1);
        entityManager.getTransaction().commit();
        
    }
    @Test
    public void shouldLockWriteWork(){
        EntityManager entityManager1 = emf.createEntityManager();
        entityManager1.getTransaction().begin();
        entityManager1.persist(Person.builder().email("przodownikR1_1@gmail.com").firstname("slawek1").disable(true).build());
        
        log.info("{}",entityManager1.createQuery("FROM Person").getResultList());
        entityManager1.getTransaction().commit();
        EntityManager entityManager = emf.createEntityManager();
        entityManager.getTransaction().begin();
        Person loaded1 = entityManager.find(Person.class, 1l,LockModeType.PESSIMISTIC_WRITE);
        log.info("{}",loaded1);
        entityManager.getTransaction().commit();
        
    }

}
