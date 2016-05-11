package pl.java.scalatech.exercise.bulk;

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
import pl.java.scalatech.repository.bulk.AddressRepo;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {  PropertiesLoader.class,JpaBulkConfig.class })
@ActiveProfiles(value = "bulk")
@Transactional
@Slf4j
@SqlDataAccount

public class JpaNativeTest {

    @Autowired
    private AddressRepo addressRepo;


    @Autowired
    private EntityManager em;

    @Test
    public void shouldBootstrap(){
      Assertions.assertThat(addressRepo.count()).isEqualTo(30);
      Assertions.assertThat(addressRepo.findByDisableIsNull()).hasSize(30);
    }


    @Test
    public void shouldBulkUpdate(){

      Assertions.assertThat(addressRepo.findByDisableTrue()).hasSize(30);
    }



}