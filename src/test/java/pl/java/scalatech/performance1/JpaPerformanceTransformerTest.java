package pl.java.scalatech.performance1;

import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;

import org.assertj.core.api.Assertions;
import org.hibernate.Session;
import org.hibernate.transform.AliasToEntityMapResultTransformer;
import org.hibernate.transform.Transformers;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.slf4j.Slf4j;
import pl.java.scalatech.config.PropertiesLoader;
import pl.java.scalatech.domain.examplePerformance1.dto.CustomerSummary;
import pl.java.scalatech.repository.customer.CustomerRepo;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { PropertiesLoader.class, JpaPerformanceOneConfig.class })
@ActiveProfiles(value = {"performance1","logger","dev"})
@Transactional
@Slf4j
@SqlDataCustomer
public class JpaPerformanceTransformerTest {

    @Autowired
    private CustomerRepo customerRepo;

    @Autowired
    private EntityManager em;

    @Test
    public void shouldBootstrap() {
        Assertions.assertThat(customerRepo.count()).isEqualTo(6);
    }
    @Test
    public void shouldTransformerMapWork() {
        Session session = em.unwrap(Session.class);
        List<Map> result =  session.createQuery("Select c.id as id, c.name as name  ,count(j) as countJob from Customer c join c.jobs j WHERE c.id = :id")
                 .setParameter("id", 5l)
                .setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE)
                .list();
        log.info("customer summary : {}",result);
        for (Map map : result) {
            Long id =  (Long) map.get("id");
            String name =  (String) map.get("name");
            long count =  (long) map.get("countJob");
            log.info("+++ id {} , name : {} , count : {}",id,name,count);
        }

    }
    @Test
    @Ignore
    //TODO
    public void shouldTransformerBeanWork() {
        Session session = em.unwrap(Session.class);
        CustomerSummary result =  (CustomerSummary) session.createQuery("Select c.id as id, c.name as name  ,count(j) as countJob from Customer c join c.jobs j WHERE c.id = :id")
                 .setParameter("id", 5l)
                .setResultTransformer(Transformers.aliasToBean(CustomerSummary.class))
                .uniqueResult();
        log.info("customer summary : {}",result);


    }

}