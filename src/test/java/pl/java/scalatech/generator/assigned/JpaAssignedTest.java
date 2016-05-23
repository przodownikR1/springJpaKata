package pl.java.scalatech.generator.assigned;

import javax.persistence.EntityManager;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.slf4j.Slf4j;
import pl.java.scalatech.config.PropertiesLoader;
import pl.java.scalatech.domain.generator.AssignedEntity;
import pl.java.scalatech.repository.generator.AssignedRepo;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { PropertiesLoader.class, JpaAssignedConfig.class })
@ActiveProfiles(value = "assigned")
@Transactional
@Slf4j

public class JpaAssignedTest {

    @Autowired
    private AssignedRepo assignedRepo;

    @Autowired
    private EntityManager em;

    @Test
    public void shouldSave() {
        AssignedEntity aEntity = new AssignedEntity();
        aEntity.setName("test");

        log.info("_+++  {}",aEntity);
        em.persist(aEntity);
        log.info("{}",assignedRepo.findAll());

    }





}