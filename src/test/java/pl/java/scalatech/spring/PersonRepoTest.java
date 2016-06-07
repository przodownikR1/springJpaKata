package pl.java.scalatech.spring;

import java.time.LocalDate;
import java.time.ZonedDateTime;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lombok.extern.slf4j.Slf4j;
import pl.java.scalatech.domain.mainPerson.Person;
import pl.java.scalatech.repository.mainPerson.PersonRepository;

@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { TestJpaPersonConfig.class })
@ActiveProfiles(value={"logger","dev"})
@Transactional
public class PersonRepoTest {
    @Autowired
    private PersonRepository personRepository;

    @Test
    public void shouldBootstrap() {

    }

    @Test
    public void shouldRepoWork() {
        personRepository.save(Person.builder().km(34d).email("przodownikR1@gmail.com").firstname("przodownik").birthDay(ZonedDateTime.now()).modify(LocalDate.now()).build());
        personRepository.save(Person.builder().km(35d).email("kalinaR1@gmail.com").firstname("kalina").birthDay(ZonedDateTime.now()).modify(LocalDate.now()).build());
        personRepository.save(Person.builder().km(77d).email("aga@gmail.com").firstname("agaee").active(true).birthDay(ZonedDateTime.now()).modify(LocalDate.now()).build());
        Person person =Person.builder().km(88d).email("bak@gmail.com").firstname("bakee").active(true).birthDay(ZonedDateTime.now()).modify(LocalDate.now()).build();
        personRepository.save(person);
        log.info("overview : {}",personRepository.findOne(1l));
        log.info("select : {}",personRepository.all());
        //log.info("overview : modify {} , effective modify {}",personRepository.findOne(1l).getModify(),personRepository.findOne(1l).getEffectiveModify());
    }
    @Test
    public void shouldShallowWork(){
        personRepository.save(Person.builder().km(34d).email("przodownikR1@gmail.com").firstname("przodownik").birthDay(ZonedDateTime.now()).modify(LocalDate.now()).build());
        personRepository.save(Person.builder().km(35d).email("kalinaR1@gmail.com").firstname("kalina").birthDay(ZonedDateTime.now()).modify(LocalDate.now()).build());
        personRepository.save(Person.builder().km(77d).email("aga@gmail.com").firstname("agaee").active(true).birthDay(ZonedDateTime.now()).modify(LocalDate.now()).build());
        Person person =Person.builder().km(88d).email("bak@gmail.com").firstname("bakee").active(true).birthDay(ZonedDateTime.now()).modify(LocalDate.now()).build();
        personRepository.save(person);
        log.info("{}",personRepository.findByFirstname("kalina"));



    }
    @Test
    public void shouldLikeWork(){
        personRepository.save(Person.builder().km(34d).email("przodownikR1@gmail.com").active(true).firstname("przodownik").birthDay(ZonedDateTime.now()).modify(LocalDate.now()).build());
        personRepository.findByEmailUseLike("przodownik");
    }

}