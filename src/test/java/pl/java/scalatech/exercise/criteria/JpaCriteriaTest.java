package pl.java.scalatech.exercise.criteria;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Tuple;
import javax.persistence.TupleElement;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.ParameterExpression;
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

import static com.google.common.collect.Lists.newArrayList;

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

    @Test
    public void shouldUseTuple(){

                String searchName = "borowiec2";
                CriteriaBuilder cb = em.getCriteriaBuilder();
                //CriteriaQuery<Object[]> c = cb.createQuery(Object[].class); //<4>
                CriteriaQuery<Tuple> criteria = cb.createQuery(Tuple.class); //<1>
                Root<Person> root = criteria.from(Person.class);//<2>
                ParameterExpression<String> lastNameParameter = cb.parameter(String.class,"lastName");
                criteria.multiselect(root.get("firstName"), root.get("version")).where(cb.equal(root.get("lastName"),lastNameParameter));

                List<Tuple> tupleResult = em.createQuery(criteria).setParameter("lastName", searchName).getResultList();//<3>
                for (Tuple t : tupleResult) {
                   log.info("fistName : {} , version : {} ",t.get(0),t.get(1));
                }
                Assertions.assertThat(tupleResult.size()).isEqualTo(1);
    }

    @Test
    public void shouldUseTupleWithAlias(){

                String searchName = "borowiec2";
                CriteriaBuilder cb = em.getCriteriaBuilder();
                //CriteriaQuery<Object[]> c = cb.createQuery(Object[].class); //<4>
                CriteriaQuery<Tuple> criteria = cb.createQuery(Tuple.class); //<1>
                Root<Person> root = criteria.from(Person.class);//<2>
                ParameterExpression<String> lastNameParameter = cb.parameter(String.class,"lastName");
                criteria.multiselect(root.get("firstName").alias("fn"), root.get("version").alias("v")).where(cb.equal(root.get("lastName"),lastNameParameter));

                List<Tuple> tupleResult = em.createQuery(criteria).setParameter("lastName", searchName).getResultList();//<3>
                for (Tuple t : tupleResult) {
                   log.info("fistName : {} , version : {} ",t.get("fn"),t.get("v"));
                 //META
                   for (TupleElement<?> element : t.getElements()) {
                       Class<?> clazz = element.getJavaType();
                       String alias = element.getAlias();
                       Object value = t.get(element);
                       log.info("class : {} , alias : {}, value : {}",clazz,alias,value);
                       }
                }
                Assertions.assertThat(tupleResult.size()).isEqualTo(1);


    }

  @Test
    public void shouldUseConstruct(){

        String searchName = "borowiec2";
        CriteriaBuilder cb = em.getCriteriaBuilder();
        //CriteriaQuery<Object[]> c = cb.createQuery(Object[].class); //<4>
        CriteriaQuery<PersonWrapper> criteria = cb.createQuery(PersonWrapper.class); //<1>
        Root<Person> root = criteria.from(Person.class);//<2>
        ParameterExpression<String> lastNameParameter = cb.parameter(String.class,"lastName");
        criteria.select(cb.construct(PersonWrapper.class, root.get("firstName"),root.get("version"))).where(cb.equal(root.get("lastName"),lastNameParameter));

        List<PersonWrapper> result = em.createQuery(criteria).setParameter("lastName", searchName).getResultList();//<3>
        for (PersonWrapper pw : result) {
           log.info("pw : {}",pw);
        }

}



    @Test
    public void shouldUseParameterExpressionToFindName(){

                String searchName = "borowiec2";
                CriteriaBuilder cb = em.getCriteriaBuilder();
                CriteriaQuery<String> criteria = cb.createQuery(String.class); //<1>
                Root<Person> root = criteria.from(Person.class);//<2>
                ParameterExpression<String> lastNameParameter = cb.parameter(String.class,"lastName"); //<3>
                criteria.select(root.get("firstName")).where(cb.equal(root.get("lastName"),lastNameParameter)); //<4>
                List<String> result  = em.createQuery(criteria).setParameter("lastName", searchName).getResultList(); //<5>
                log.info("{}",result);


    }

    @Test
    public void shouldUseParameterExpressionToFindLikeName(){

                String searchName = "borowie%";
                CriteriaBuilder cb = em.getCriteriaBuilder();
                CriteriaQuery<String> criteria = cb.createQuery(String.class);
                Root<Person> root = criteria.from(Person.class);
                ParameterExpression<String> lastNameParameter = cb.parameter(String.class,"lastName");
                criteria.select(root.get("firstName")).where(cb.like(root.get("lastName"),lastNameParameter));
                criteria.orderBy(cb.desc(root.get("firstName")));
                List<String> result  = em.createQuery(criteria).setParameter("lastName", searchName).getResultList();
                log.info("{}",result);


    }
    @Test
    public void shouldFindLikeLowerName(){

                String searchName = "borowie%";
                CriteriaBuilder cb = em.getCriteriaBuilder();
                CriteriaQuery<String> criteria = cb.createQuery(String.class);
                Root<Person> root = criteria.from(Person.class);
                ParameterExpression<String> lastNameParameter = cb.parameter(String.class,"lastName");
                criteria.select(root.get("firstName")).where(cb.like(cb.lower(root.get("lastName")),lastNameParameter));
                criteria.orderBy(cb.desc(root.get("firstName")));
                List<String> result  = em.createQuery(criteria).setParameter("lastName", searchName).getResultList();
                log.info("{}",result);


    }

    @Test
    public void shouldQueryRootsWork(){
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Person> c = cb.createQuery(Person.class);
        Root<Person> person = c.from(Person.class);
        Root<Address> address = c.from(Address.class);
        c.select(person)
        .distinct(true)
        .where(cb.equal(person, address.get("person")));
        List<Person> result  = em.createQuery(c).getResultList();
        log.info("result : {}", result);

    }


    @Test
    //TODO
    public void shouldInWork(){
        List<String> cityNames = newArrayList("borowiec1,borowiec2");
        String []cityNamesArray = cityNames.toArray(new String[0]);
        log.info("++++++++++++++  {}",cityNamesArray);

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Person> c = cb.createQuery(Person.class);
        Root<Person> person = c.from(Person.class);

        c.select(person).where(cb.in(person.<String>get("lastName")).value("borowiec1").value("borowiec2"));
        //c.select(person).where(cb.in(person.<String>get("lastName")).value(cityNamesArray));
        List<Person> result  = em.createQuery(c).getResultList();
        Assertions.assertThat(result).hasSize(2);

    }

}
