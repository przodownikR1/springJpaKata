package pl.java.scalatech.exercise.selfRef;

import static org.junit.runners.MethodSorters.NAME_ASCENDING;

import org.assertj.core.api.Assertions;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.slf4j.Slf4j;
import pl.java.scalatech.config.JpaLoggerConfig;
import pl.java.scalatech.config.hikari.HikariCPConfiguration;
import pl.java.scalatech.domain.selfReference.Employee;
import pl.java.scalatech.repository.selfReference.EmployeeRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={TestSelectorConfig.class,JpaLoggerConfig.class,HikariCPConfiguration.class})
@FixMethodOrder(NAME_ASCENDING)
@ActiveProfiles(profiles={"logger","test","dev"})
@Transactional
@Slf4j
public class SelfReferenceTest {
    @Autowired
    private EmployeeRepository employeeRepo;

    @Test
    public void shouldRepoAutoWired() {
        Assertions.assertThat(employeeRepo).isNotNull();
    }

    @Test
    public void should_A_saveEmployee() {
        log.info("+++ SAVE_EMPLOYEE");
        Employee manager1 = new Employee("przodownik", "pracy");

        Employee employee1 = new Employee("kowalski", "Jan");
        Employee employee2 = new Employee("nowak", "karol");

        employee1.setManager(manager1);
        employee2.setManager(manager1);
        employeeRepo.save(employee1);
        employeeRepo.save(employee2);
        log.info("employee 1 {}",employeeRepo.findByLastname("Jan"));
        log.info("employee 1 manager {}",employeeRepo.findByLastname("Jan").getManager());
        log.info("employee 2 {}", employeeRepo.findByLastname("karol"));
        log.info("employee 2 manager {} ", employeeRepo.findByLastname("karol").getManager());
        employeeRepo.findAll().forEach(emploee -> log.info("{}", emploee));

    }

    @Test
    public void should_B_saveEmployee() {
        log.info("+++ SAVE_EMPLOYEE");
        Employee manager1 = new Employee("przodownik", "pracy");
        Employee employee1 = new Employee("kowalski", "Jan");
        Employee employee2 = new Employee("nowak", "karol");
       // manager1.setEmployees(Sets.newHashSet(employee1,employee2));

        manager1.addEmployee(employee1);
        manager1.addEmployee(employee2);
        employeeRepo.save(manager1);
        Assertions.assertThat(employeeRepo.findByLastname("pracy").getEmployees()).isNotEmpty().hasSize(2);
        log.info("{}" ,employeeRepo.findByLastname("Jan"));
       // employeeRepo.findAll().forEach(emploee -> log.info("b: {}", emploee));

    }

}
