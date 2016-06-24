package pl.java.scalatech.exercise.jpql;

import java.util.List;

import javax.persistence.EntityManager;

import org.assertj.core.api.Assertions;
import org.hibernate.Session;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.slf4j.Slf4j;
import pl.java.scalatech.config.PropertiesLoader;
import pl.java.scalatech.domain.jpql.Address;
import pl.java.scalatech.domain.jpql.Company;
import pl.java.scalatech.repository.jpql.CompanyRepo;

//tag::main[]
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { PropertiesLoader.class, JpaJpqlConfig.class })
@ActiveProfiles(value = "jpql")
@Transactional
@SqlDataJPQL
@Slf4j
public class JpqlByExampleTest {
    @Autowired
    private CompanyRepo companyRepo;

    @Autowired
    private EntityManager em;

    @Test
    public void shouldCriteriaByExampleWork() {
        Company template = Company.builder().name("javatech").address(Address.builder().city("radom").street("koncowa").build()).build();
        org.hibernate.criterion.Example example = org.hibernate.criterion.Example.create(template); //<1>
        example.ignoreCase(); //<2>
        // example.enableLike(MatchMode.START);
        example.excludeProperty("depts"); //<2>
        @SuppressWarnings("unchecked")
        List<Company> company = em.unwrap(Session.class).createCriteria(Company.class).add(example).list();
        Assertions.assertThat(company).hasSize(1);
    }

    @Test
    public void shouldCriteriaByExampleFailed() {
        Company template = Company.builder().name("javatech").address(Address.builder().city("radom").street("polna").build()).build();
        org.hibernate.criterion.Example example = org.hibernate.criterion.Example.create(template); 
        example.ignoreCase(); 
        // example.enableLike(MatchMode.START);
        example.excludeProperty("depts");
        @SuppressWarnings("unchecked")
        List<Company> company = em.unwrap(Session.class).createCriteria(Company.class).add(example).list();
        Assertions.assertThat(company).hasSize(0);
    }

    @Test
    public void shouldUseExample() {
        Company template = Company.builder().name("javatech").address(Address.builder().city("radom").street("koncowa").build()).build();
        ExampleMatcher matcher = ExampleMatcher.matching().withIgnorePaths("depts").withIncludeNullValues(); //<2>
        Example<Company> example = Example.of(template,matcher);
        companyRepo.findAll(example);
    }
}
// end::main[]