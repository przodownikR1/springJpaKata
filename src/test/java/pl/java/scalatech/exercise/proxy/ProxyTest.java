package pl.java.scalatech.exercise.proxy;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.PersistenceUtil;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.slf4j.Slf4j;
import pl.java.scalatech.config.PropertiesLoader;
import pl.java.scalatech.domain.fetching.Person;
import pl.java.scalatech.exercise.fetch.JpaFetchConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {  PropertiesLoader.class,JpaFetchConfig.class })
@ActiveProfiles(value = "fetch")
@Transactional
@Slf4j
//@SqlDataAccount
@Sql(scripts="classpath:fetch.sql")
public class ProxyTest {


@Autowired
private EntityManager em;

@Test
public void shouldRetrieveRef(){

    Person p =  em.getReference(Person.class, 1l);
    p.setLastName("ronak");

  Assertions.assertThat(p.getClass()).isNotInstanceOf(Person.class);
   Assertions.assertThat(p.getId()).isEqualTo(1l);
   PersistenceUtil persistenceUtil = Persistence.getPersistenceUtil();
   log.info("{}",persistenceUtil.isLoaded(p));

}

@Test
public void shouldRetrieveFind(){
   Person p =  em.find(Person.class, 1l);
   p.setLastName("ronak");
  Assertions.assertThat(p.getClass()).isNotInstanceOf(Person.class);
   Assertions.assertThat(p.getId()).isEqualTo(1l);
   PersistenceUtil persistenceUtil = Persistence.getPersistenceUtil();
   log.info("{}",persistenceUtil.isLoaded(p));
}

}
