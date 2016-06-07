package pl.java.scalatech.exercise.nativeQuery;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.assertj.core.api.Assertions;
import org.hibernate.Session;
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
import pl.java.scalatech.repository.fetch.AddressRepo;
import pl.java.scalatech.repository.fetch.PersonRepo;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {  PropertiesLoader.class,JpaNativeConfig.class })
@ActiveProfiles(value = {"native","dev"})
@Transactional
@Slf4j
//@SqlDataAccount
@Sql(scripts="classpath:fetch.sql")
public class JpaNativeTest {

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
    public void shouldNativeQueryWork(){
      Query query = em.createNativeQuery("select * from Fetch_Person",Person.class);
       List<Person> result = query.getResultList();
       log.info("result {}", result);
       Assertions.assertThat(result).hasSize(12);
    }

    @Test
    public void shouldSqlQueryWork(){
        org.hibernate.SQLQuery query = em.unwrap(Session.class).createSQLQuery("select * from Fetch_Person");
                query.addEntity(Person.class);
                List<Person> result = query.list();
                log.info("result :{} ",result);
                Assertions.assertThat(result).hasSize(12);
    }
    //TODO
    @Test
    public void shouldSqlQueryMappingWork(){
        org.hibernate.SQLQuery query = em.unwrap(Session.class).createSQLQuery("select id,firstName,lastName,version from Fetch_Person");
        query.setResultSetMapping("personResult");
        List<Person> result = query.list();
        log.info("result :{} ",result);
         Assertions.assertThat(result).hasSize(12);
    }


    @Test
    public void shouldSqlQueryNativeNamedWork(){
         Person loaded = (Person) em.createNamedQuery("queryPerson").setParameter("id", 1l).getSingleResult();
         Assertions.assertThat(loaded.getFirstName()).isNotNull().isEqualTo("slawek1");
    }

    @Test
    public void shouldSqlQueryNativeNamedByFirstNameWork(){
         Person loaded = (Person) em.createNamedQuery("findPersonByfistName").setParameter("firstName", "slawek6").getSingleResult();
         Assertions.assertThat(loaded.getFirstName()).isNotNull().isEqualTo("slawek6");
    }

    @Test
    public void shouldSqlQueryNativeWork(){
         Object object = em.createNativeQuery("select id,firstName,lastName,version from Fetch_Person p where p.id = :id","personResult").setParameter("id", 1l).getSingleResult();
               log.info("clazz : {} , value : {} ", object.getClass().getSimpleName(),object.toString());
    }

    @Test
    public void shouldMappingNativeQueryToEntityClass(){
        Person result =  (Person)em.createNativeQuery("select * from Fetch_Person p where p.id = :id",Person.class).setParameter("id", 1l).getSingleResult();
        log.info("result :{}",result);
    }

}