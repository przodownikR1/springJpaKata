package pl.java.scalatech.exercise.filter;

import java.time.ZonedDateTime;

import javax.persistence.EntityManager;

import org.assertj.core.api.Assertions;
import org.hibernate.Session;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.slf4j.Slf4j;
import pl.java.scalatech.config.PropertiesLoader;
import pl.java.scalatech.domain.Person;
import pl.java.scalatech.repository.PersonRepository;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { PropertiesLoader.class, JpaFilterConfig.class })
@Transactional
@ActiveProfiles("filter")
@Slf4j
public class FilterTest {

    @Autowired
    private PersonRepository personRepository;
    @Autowired
    private EntityManager entityManager;
    @Before
    public void init(){
        personRepository.save(Person.builder().email("przodownikR1_1@gmail.com").firstname("slawek1").disable(true).active(true).birthDay(ZonedDateTime.now()).build());
        personRepository.save(Person.builder().email("przodownikR1_2@gmail.com").firstname("slawek2").disable(true).active(true).birthDay(ZonedDateTime.now()).build());
        personRepository.save(Person.builder().email("przodownikR1_3@gmail.com").firstname("slawek3").disable(false).active(false).birthDay(ZonedDateTime.now()).build());
        personRepository.save(Person.builder().email("przodownikR1_4@gmail.com").firstname("slawek4").disable(true).active(true).birthDay(ZonedDateTime.now()).build());
        personRepository.save(Person.builder().email("przodownikR1_5@gmail.com").firstname("slawek5").disable(false).active(false).birthDay(ZonedDateTime.now()).build());
    }

    @Test
    public void shouldFilterWork(){
        Assertions.assertThat(personRepository.count()).isEqualTo(5);
       Session session =  entityManager.unwrap(Session.class);
       log.info("+++++++++++++++++ {}",session);
       session.enableFilter("byStatus").setParameter("active", true);
       //TODO
      // long active = (long) session.createQuery("select count(*) from Person").uniqueResult();
      // Assertions.assertThat(active).isEqualTo(3);
/*


        session =  (Session) entityManager.unwrap(Session.class).enableFilter("byStatus").setParameter("status", false);
       int disActive = session.createQuery("select count(*) from Person").getFirstResult();
       Assertions.assertThat(disActive).isEqualTo(2);*/

    }

}
