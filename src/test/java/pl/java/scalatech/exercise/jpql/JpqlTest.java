package pl.java.scalatech.exercise.jpql;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.slf4j.Slf4j;
import pl.java.scalatech.config.PropertiesLoader;
import pl.java.scalatech.domain.jpql.Company;
import pl.java.scalatech.repository.jpql.CompanyRepo;
import pl.java.scalatech.repository.jpql.DeptRepo;
import pl.java.scalatech.repository.jpql.EmployeeRepo;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {  PropertiesLoader.class,JpaJpqlConfig.class })
@ActiveProfiles(value = "jpql")
@Transactional
@SqlDataJPQL
@Slf4j
public class JpqlTest {

    @Autowired
    private CompanyRepo companyRepo;

    @Autowired
    private DeptRepo deptRepo;

    @Autowired
    private EmployeeRepo employeeRepo;

    @Autowired
    private EntityManager em;

    @Test
    public void shouldBoostrap(){
         Assertions.assertThat(companyRepo.count()).isEqualTo(6);
         Assertions.assertThat(deptRepo.count()).isEqualTo(7);
         Assertions.assertThat(employeeRepo.count()).isEqualTo(16);
    }

    //tag::main[]
    @Test
    public void shouldProgrammaticallyNamedQueries(){
        Query findCompanyQuery = em.createQuery("select c from Company c"); 
        em.getEntityManagerFactory().addNamedQuery("companyQuery", findCompanyQuery); //<1>
        TypedQuery<Company> query = em.createNamedQuery("companyQuery",Company.class); //<2>
        log.info("{}",query.getResultList());
    }
    // end::main[]

}
