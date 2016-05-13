package pl.java.scalatech.exercise.inheritence.single;

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
import pl.java.scalatech.domain.inheritence.single.GroovyTask;
import pl.java.scalatech.domain.inheritence.single.JavaTask;
import pl.java.scalatech.domain.inheritence.single.JpaTask;
import pl.java.scalatech.domain.inheritence.single.Person;
import pl.java.scalatech.repository.inheritence.single.GroovyTaskRepo;
import pl.java.scalatech.repository.inheritence.single.JavaTaskRepo;
import pl.java.scalatech.repository.inheritence.single.JpaTaskRepo;
import pl.java.scalatech.repository.inheritence.single.TaskRepo;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { PropertiesLoader.class, SingleTableConfig.class })
@ActiveProfiles(value = "single")
@Transactional
@Slf4j
public class SingleTableTest {

    @Autowired
    private TaskRepo taskRepo;

    @Autowired
    private JavaTaskRepo javaTaskRepo;

    @Autowired
    private GroovyTaskRepo groovyTaskRepo;

    @Autowired
    private JpaTaskRepo jpaTaskRepo;



    @Test
    public void shouldWork(){
        Person person = Person.builder().email("przodownikR1@gmail.com").firstname("slawek").build();
        taskRepo.save(new JavaTask("classWrite",12,person,"extractMethod"));
        taskRepo.save(new GroovyTask("task",34,person,"todo"));
        taskRepo.save(new JpaTask("mapping",1,person,"bidirectional"));




        Assertions.assertThat(javaTaskRepo.count()).isEqualTo(1);
        Assertions.assertThat(groovyTaskRepo.count()).isEqualTo(1);
        Assertions.assertThat(jpaTaskRepo.count()).isEqualTo(1);
        Assertions.assertThat(taskRepo.count()).isEqualTo(3);


    }
}
