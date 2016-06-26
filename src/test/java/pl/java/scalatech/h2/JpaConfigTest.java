package pl.java.scalatech.h2;

import static org.assertj.core.api.Assertions.assertThat;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.slf4j.Slf4j;
import pl.java.scalatech.config.PropertiesLoader;
import pl.java.scalatech.config.h2.JpaEmbeddedConfig;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {  PropertiesLoader.class,JpaEmbeddedConfig.class })
@ActiveProfiles(value = "h2-test")
@Transactional
@Slf4j
public class JpaConfigTest {
    @Autowired
    private EntityManagerFactory emf;

    @Autowired
    private EntityManager em;

    @Test
    public void shouldBootstrap(){
        assertThat(emf).isNotNull();
        assertThat(em).isNotNull();
    }
}

