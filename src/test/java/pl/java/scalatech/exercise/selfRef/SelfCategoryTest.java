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
import pl.java.scalatech.domain.selfReference.Category;
import pl.java.scalatech.repository.selfReference.SelfCategoryRepo;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={TestSelectorConfig.class,JpaLoggerConfig.class})
@FixMethodOrder(NAME_ASCENDING)
@ActiveProfiles(profiles={"logger","test"})
@Transactional
@Slf4j
public class SelfCategoryTest {
    @Autowired
    private SelfCategoryRepo repo;
    @Test
    public void shouldRepoAutoWired(){
        Assertions.assertThat(repo).isNotNull();
    }
    @Test
    public void should_A_saveCategory(){
        log.info("+++ SAVE Tank");
        Category master = new Category();
        master.setName("tank");

        Category light = new Category();
        light.setName("lightTank");
        light.setParent(master);

        Category heavy = new Category();
        heavy.setName("heavyTank");
        heavy.setParent(master);
        repo.save(light);
        repo.save(heavy);
        repo.findAll().forEach(emploee -> log.info("{}",emploee));

    }

}
