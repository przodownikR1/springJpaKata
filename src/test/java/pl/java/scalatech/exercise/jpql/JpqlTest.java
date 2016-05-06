package pl.java.scalatech.exercise.jpql;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.slf4j.Slf4j;
import pl.java.scalatech.config.PropertiesLoader;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {  PropertiesLoader.class,JpaJpqlConfig.class })
@ActiveProfiles(value = "jpql")
@Transactional
@Slf4j
public class JpqlTest {

    @Test
    public void shouldBoostrap(){

    }

}
