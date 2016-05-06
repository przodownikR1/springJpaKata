package pl.java.scalatech.exercise.jpql;

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

    @Test
    public void shouldBoostrap(){
         Assertions.assertThat(companyRepo.count()).isEqualTo(6);
         Assertions.assertThat(deptRepo.count()).isEqualTo(7);
         Assertions.assertThat(employeeRepo.count()).isEqualTo(16);
    }

}
