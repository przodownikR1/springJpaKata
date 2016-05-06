package pl.java.scalatech.exercise.fetch;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Repeat;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.slf4j.Slf4j;
import pl.java.scalatech.config.PropertiesLoader;
import pl.java.scalatech.domain.fetching.Person;
import pl.java.scalatech.repository.fetch.AddressRepo;
import pl.java.scalatech.repository.fetch.PersonRepo;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {  PropertiesLoader.class,JpaFetchConfig.class })
@ActiveProfiles(value = "fetch")
@Transactional
@Slf4j
//@SqlDataAccount
@Sql(scripts="classpath:fetch.sql")
public class JpaFetchTest {

    @Autowired
    private AddressRepo addressRepo;

    @Autowired
    private PersonRepo personRepo;

    @Autowired
    private EntityManager em;

    @Test
    public void shouldBootstrap(){
      assertThat(addressRepo.count()).isEqualTo(30);
      assertThat(personRepo.count()).isEqualTo(12);
    }

    @Test
    public void shouldRetrievePerson(){
       List<Person> people = personRepo.findAll();
       log.info("people {}",people);

    }

    @Test
    @Repeat(10)
    public void shouldRetrievePersonEM(){
       em.createQuery("FROM Person",Person.class).getResultList().forEach(p -> log.info("person : {}",p));
       log.info("{}",Persistence.getPersistenceUtil().isLoaded(em.createQuery("FROM Person",Person.class).getResultList().get(0).getAddresses()));


    }

}
