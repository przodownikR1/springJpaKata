package pl.java.scalatech.collection.map.basic;

import static com.google.common.collect.Maps.newHashMap;

import java.util.Map;

import javax.persistence.EntityManager;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Maps;

import lombok.extern.slf4j.Slf4j;
import pl.java.scalatech.config.JpaLoggerConfig;
import pl.java.scalatech.config.PropertiesLoader;
import pl.java.scalatech.domain.mapkey.basic_mapKey.Country;
import pl.java.scalatech.domain.mapkey.basic_mapKey.State;
import pl.java.scalatech.repository.map.basic.CountryRepo;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { PropertiesLoader.class, JpaMapConfig.class, JpaLoggerConfig.class })
@ActiveProfiles(value = { "mapKey", "logger", "dev" })
@Transactional
@Slf4j
public class MapKeyTest {

    @Autowired
    private EntityManager em;
    
    @Autowired
    private CountryRepo countryRepo;

    @Test
    public void shouldBootstrap() {
        Map<Integer,State> states = newHashMap();
        states.put(1, State.builder().name("mazowieckie").build());
        states.put(2, State.builder().name("slaskie").build());
        states.put(3, State.builder().name("malopolskie").build());
        Country country = Country.builder().name("Poland").states(states).build();
        countryRepo.save(country);
        states = newHashMap();
        states.put(1, State.builder().name("schlewst").build());
        states.put(2, State.builder().name("bawaria").build());
        country = Country.builder().name("German").states(states).build();
        countryRepo.save(country);
        
        log.info("{}",countryRepo.findAll());
    }

    
    
}
