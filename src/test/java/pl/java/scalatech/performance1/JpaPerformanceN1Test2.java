package pl.java.scalatech.performance1;

import javax.persistence.EntityManager;

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
import pl.java.scalatech.repository.customer.CustomerRepo;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { PropertiesLoader.class, JpaPerformanceOneConfig.class })
@ActiveProfiles(value = {"performance1","logger","dev"})
@Transactional
@Slf4j
@SqlDataCustomer
public class JpaPerformanceN1Test2 {

    @Autowired
    private CustomerRepo customerRepo;

    @Autowired
    private EntityManager em;

    @Test
    public void shouldBootstrap() {
        Assertions.assertThat(customerRepo.count()).isEqualTo(6);
    }
    @Test
    public void shouldN1Explain() {
        customerRepo.findAll().stream().forEach(c-> log.info(" c : {} -has size job : {}",c,c.getJobs().size()));
    }


}