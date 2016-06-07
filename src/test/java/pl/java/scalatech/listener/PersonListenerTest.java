package pl.java.scalatech.listener;

import java.time.LocalDate;
import java.time.ZonedDateTime;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.slf4j.Slf4j;
import pl.java.scalatech.config.TestJpaConfig;
import pl.java.scalatech.domain.mainPerson.Person;
import pl.java.scalatech.repository.mainPerson.PersonRepository;

@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { TestJpaConfig.class })
@ActiveProfiles(value={"logger","dev"})
@Transactional
public class PersonListenerTest {
    @Autowired
    private PersonRepository personRepository;

    @Test
    public void shouldBootstrap() {

    }

    @Test
    //@Ignore //z eclipse przechodzi !!
    public void shouldListenerWorkLikeCustom() {

        Person person =Person.builder().km(88d).email("bak@gmail.com").firstname("addAction").birthDay(ZonedDateTime.now()).modify(LocalDate.now()).build();
        Person person1 = personRepository.save(person);
             
        Person loaded = personRepository.findByEmailUseLike("bak");
        log.info("+++++  loaded : {}",loaded);                       
        Assertions.assertThat(loaded.getActive()).isTrue();
    }


    @Test  
    public void shouldListenerWorkLikeIs() {

        Person person =Person.builder().km(88d).email("bak@gmail.com").firstname("addAction").birthDay(ZonedDateTime.now()).modify(LocalDate.now()).build();
        Person person1 = personRepository.save(person);
             
        Person loaded = personRepository.findByEmailIsLike("bak%");
        log.info("+++++  loaded : {}",loaded);                       
        Assertions.assertThat(loaded.getActive()).isTrue();
    }
    
    @Test  
    public void shouldListenerWorkLike() {

        Person person =Person.builder().km(88d).email("bak@gmail.com").firstname("addAction").birthDay(ZonedDateTime.now()).modify(LocalDate.now()).build();
        Person person1 = personRepository.save(person);
             
        Person loaded = personRepository.findByEmailLike("bak%");
        log.info("+++++  loaded : {}",loaded);                       
        Assertions.assertThat(loaded.getActive()).isTrue();
    }
}