package pl.java.scalatech.constructorResult;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lombok.extern.slf4j.Slf4j;
import pl.java.scalatech.config.TestJpaConfig;
import pl.java.scalatech.repository.constructorResult.ConstructorResultRepo;

@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={TestJpaConfig.class})
public class ConstructorResultTest {
    @Autowired
    private ConstructorResultRepo repo;

    @Test
    public void shouldSave(){
        assertThat(repo).isNotNull();
    }

}
