package pl.java.scalatech.exercise.one2many;

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

import com.google.common.collect.Lists;

import lombok.extern.slf4j.Slf4j;
import pl.java.scalatech.config.JpaLoggerConfig;
import pl.java.scalatech.domain.example.one2many.Address;
import pl.java.scalatech.domain.example.one2many.User;
import pl.java.scalatech.repository.one2many.AddressRepo;
import pl.java.scalatech.repository.one2many.UserRepo;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={TestSelectorConfig.class,JpaLoggerConfig.class})
@FixMethodOrder(NAME_ASCENDING)
@ActiveProfiles(profiles={"logger","test"})
@Transactional
@Slf4j
public class One2ManyBiTest {
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private AddressRepo addressRepo;

    @Test
    public void should_A_saveUser() {
        User user = User.builder().login("przodownik").address(Lists.newArrayList()).build();
        Address address = Address.builder().street("polna").build();
        user.getAddress().add(address);
        userRepo.save(user);
        log.info("{} address {}",userRepo.findOne(1l),userRepo.findOne(1l).getAddress());
        Assertions.assertThat(userRepo.findOne(1l).getAddress()).hasSize(1);
        Assertions.assertThat(userRepo.findOne(1l).getAddress().get(0).getUser()).isNull();
    }
    @Test
    public void should_B_saveUser() {
        User user = User.builder().login("przodownik").address(Lists.newArrayList()).build();
        user.addAddress(Address.builder().street("wolna").build());
        userRepo.save(user);
        Assertions.assertThat(userRepo.findOne(1l).getAddress()).hasSize(1);
        Assertions.assertThat(userRepo.findOne(1l).getAddress().get(0).getUser()).isNotNull();
        log.info("{} address {}",userRepo.findOne(1l),userRepo.findOne(1l).getAddress());
    }

    @Test
    public void should_A_saveAddress(){
         Address address = Address.builder().street("jerozolimskie").build();
         User user = User.builder().login("przodownik").build();
         address.setUser(user);
         addressRepo.save(address);
         log.info("address : {}, user address :{}",addressRepo.findOne(1l),addressRepo.findOne(1l).getUser().getAddress());
         Assertions.assertThat(addressRepo.findOne(1l).getUser().getAddress()).isNull();

    }
    @Test
    public void should_B_saveAddress(){
         Address address = Address.builder().street("jerozolimskie").build();
         User user = User.builder().address(Lists.newArrayList()).login("przodownik").build();
         user.getAddress().add(address);
         address.setUser(user);
         addressRepo.save(address);
         log.info("address : {}, user address :{}",addressRepo.findOne(1l),addressRepo.findOne(1l).getUser().getAddress());
         Assertions.assertThat(addressRepo.findOne(1l).getUser().getAddress()).isNotNull();
    }

}
