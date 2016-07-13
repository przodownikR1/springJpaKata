package pl.java.scalatech.exercise.cartesian;

import static org.assertj.core.api.Assertions.assertThat;

import javax.persistence.EntityManager;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.slf4j.Slf4j;
import pl.java.scalatech.config.JpaLoggerConfig;
import pl.java.scalatech.config.PropertiesLoader;
import pl.java.scalatech.config.hikari.HikariCPConfiguration;
import pl.java.scalatech.domain.cartesian.Person;
import pl.java.scalatech.repository.cartesian.AddressCartRepo;
import pl.java.scalatech.repository.cartesian.PersonCartRepo;
import pl.java.scalatech.repository.cartesian.PhoneRepo;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {  PropertiesLoader.class,JpaCartasianConfig.class,JpaLoggerConfig.class ,HikariCPConfiguration.class})
@ActiveProfiles(value = {"cartasian","logger","dev"})
@Transactional
@Slf4j
//@SqlDataAccount
//tag::main[]
@Sql(scripts="classpath:cartasian.sql")
public class JpaCartasianTest {

    @Autowired
    private AddressCartRepo addressRepo;

    @Autowired
    private PersonCartRepo personRepo;

    @Autowired
    private PhoneRepo phoneRepo;

    @Autowired
    private EntityManager em;

    @Test
    public void shouldBootstrap(){
      assertThat(addressRepo.count()).isEqualTo(30);//<1>
      assertThat(personRepo.count()).isEqualTo(12);//<2>
      assertThat(phoneRepo.count()).isEqualTo(8);//<3>
    }

    @Test
    public void shouldFindPersonById(){
      Person person = personRepo.findOne(1l);//
      log.info("person : {} , address size : {} , phone size {} ",person, person.getAddresses().size(),person.getPhones().size());
      //<4>
    }
// end::main[]

}
