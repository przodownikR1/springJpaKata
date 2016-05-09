package pl.java.scalatech.exercise.criteria;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;

import lombok.extern.slf4j.Slf4j;
import pl.java.scalatech.config.PropertiesLoader;
import pl.java.scalatech.domain.fetching.Address;
import pl.java.scalatech.domain.fetching.Person;
import pl.java.scalatech.repository.fetch.AddressRepo;
import pl.java.scalatech.repository.fetch.PersonRepo;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {  PropertiesLoader.class,JpaCriteriaConfig.class })
@ActiveProfiles(value = "fetch")
@Transactional
@Slf4j
//@SqlDataAccount
@Sql(scripts="classpath:fetch.sql")
public class JpaCriteriaTest {

    @Autowired
    private AddressRepo addressRepo;

    @Autowired
    private PersonRepo personRepo;

    @Autowired
    private EntityManager em;



    @Test
    public void shouldFetchJoinWork(){
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery criteria = cb.createQuery();
        Root<Person> i = criteria.from(Person.class);
        i.fetch("addresses", JoinType.LEFT);
        criteria.select(i);
        List<Person> people = em.createQuery(criteria).getResultList();
        for (Person p : people) {
           log.info(" name : {} , address : {}",p.getLastName(),p.getAddresses());
        }
    }

    @Test
    public void shouldJoinWork(){
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Address> criteria = cb.createQuery(Address.class);
        Root<Person> i = criteria.from(Person.class);
        Join<Person, Address> join = i.join("addresses",JoinType.LEFT);
        criteria.select(join);
        List<Address> addresses = em.createQuery(criteria).getResultList();
        for (Address  address : addresses) {
           log.info(" address :{} user : {}",address,address.getPerson().getLastName());
        }
    }

    @Test
    public void shouldGetRightPerson(){
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Person> criteria = cb.createQuery(Person.class);
        Root<Person> i = criteria.from(Person.class);
        List<Predicate> andList = new ArrayList<>();
        Predicate personName = cb.equal(i.get("firstName"), "slawek2");
        andList.add(personName);
        criteria.select(i).where(cb.and(andList.toArray(new Predicate[0])));
        Person person = em.createQuery(criteria).getSingleResult();

        Assertions.assertThat(person).isNotNull();
        Assertions.assertThat(person.getLastName()).isEqualTo("borowiec2");

    }


    @Test
    public void shouldGetOrConditionPerson(){
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Person> criteria = cb.createQuery(Person.class);
        Root<Person> i = criteria.from(Person.class);
        List<Predicate> andList = new ArrayList<>();
        Predicate personName = cb.equal(i.get("firstName"), "slawek2");
        andList.add(personName);
        Predicate personName2 = cb.equal(i.get("firstName"), "slawek3");
        andList.add(personName2);
        criteria.select(i).where(cb.or(andList.toArray(new Predicate[0])));
        List<Person> persons = em.createQuery(criteria).getResultList();
        Assertions.assertThat(persons).isNotNull();
        Assertions.assertThat(persons).hasSize(2);

    }

    @Test
    public void shouldGetRightPersonWithAddressJoinWork(){
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Person> criteria = cb.createQuery(Person.class);
        Root<Person> from = criteria.from(Person.class);
        Path<String> path = from.join("addresses").get("city");
        criteria.select(from).where(cb.equal(path, "lezajsk"));
        List<Person> persons = em.createQuery(criteria).getResultList();
        Assertions.assertThat(persons).hasSize(1);
        Assertions.assertThat(persons.get(0).getAddresses()).hasSize(2);
    }



    @Test
    public void shouldPeopleJoinWork(){
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Person> criteria = cb.createQuery(Person.class);
        Root<Person> i = criteria.from(Person.class);
        Join<Address, Person> join =i.join("addresses",JoinType.LEFT);
        criteria.select(i).where(cb.equal(join.get("city"), "lezajsk2"));
        List<Person> persons = em.createQuery(criteria).getResultList();

        for (Person  person : persons) {
           log.info(" +++ persons :{}",person);
        }
    }

    @Test
    public void shouldQueryByPseudoExampleWork(){
        List<Address> addresses = Lists.newArrayList(Address.builder().city("lezajsk").build());
        Person personExample = Person.builder().addresses(addresses).build();


            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<Person> criteriaQuery = cb.createQuery(Person.class);
            Root<Person> root = criteriaQuery.from(Person.class);
            criteriaQuery.select(root).distinct(true);
            Predicate criteria = cb.conjunction();
            if (personExample.getId() != null) {
                Predicate p = cb.equal(root.get("id"), personExample.getId());
                criteria = cb.and(criteria, p);
            }else{
                if (personExample.getFirstName() != null) {
                    Predicate p = cb.equal(root.get("firstName"), personExample.getFirstName());
                    criteria = cb.and(criteria, p);
                }
                if (personExample.getLastName() != null) {
                    Predicate p = cb.equal(root.get("lastName"), personExample.getLastName());
                    criteria = cb.and(criteria, p);
                }
                if(personExample.getAddresses()!= null && !personExample.getAddresses().isEmpty()){
                    Join<Address, Person> join =root.join("addresses",JoinType.LEFT);
                    Predicate p = cb.equal(join.get("city"), personExample.getAddresses().get(0).getCity());
                    criteria = cb.and(criteria, p);
                }

            }
            criteriaQuery.where(criteria);
            List<Person> result  = em.createQuery(criteriaQuery).getResultList();
            log.info("result : {}",result);





    }

}
