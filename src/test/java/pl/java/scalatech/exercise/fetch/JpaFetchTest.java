package pl.java.scalatech.exercise.fetch;

import javax.persistence.EntityManager;

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
import pl.java.scalatech.domain.fetching.SqlDataAccount;
import pl.java.scalatech.repository.fetch.AddressRepo;
import pl.java.scalatech.repository.fetch.PersonRepo;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {  PropertiesLoader.class,JpaFetchConfig.class })
@ActiveProfiles(value = "fetch")
@Transactional
@Slf4j
@SqlDataAccount
public class JpaFetchTest {

    @Autowired
    private AddressRepo addressRepo;

    @Autowired
    private PersonRepo personRepo;

    @Autowired
    private EntityManager em;

    @Test
    public void shouldBootstrap(){
      Assertions.assertThat(addressRepo.count()).isEqualTo(15);
      Assertions.assertThat(personRepo.count()).isEqualTo(12);
    }

}
