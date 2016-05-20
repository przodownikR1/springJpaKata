package pl.java.scalatech.exercise.validation;

import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.assertj.core.api.Assertions;
import org.junit.Test;

import lombok.extern.slf4j.Slf4j;
import pl.java.scalatech.domain.mainPerson.Person;

@Slf4j
public class ValidationPersonTest {

    @Test
    public void shouldValidate() {
         Person person =  Person.builder()
                 .km(34d)
                 .email("przodownikR1gmail.com")
                 .firstname(null)
                 .birthDay(ZonedDateTime.now())
                 .modify(LocalDate.now())
                 .build();

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        Set<ConstraintViolation<Person>> validators = validator.validate(person);
        Assertions.assertThat(validators).hasSize(2);
        ConstraintViolation<Person> validation = validators.iterator().next();
        for(ConstraintViolation<Person> constraint : validators){
            //log.info("{}",constraint);
            log.info("property name  : {}, message : {} , messageTemplate : {}",constraint.getPropertyPath(),constraint.getMessage(),constraint.getMessageTemplate());
        }

      Assertions.assertThat(validators.stream().filter(p->p.getMessage().contains("not be null")).findFirst().isPresent());
     }

}
