package pl.java.scalatech.collection.map.userMap;

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
import pl.java.scalatech.domain.bags.UserMap;
import pl.java.scalatech.repository.map.mapColumn.UserMapRepo;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { PropertiesLoader.class, JpaUserMapConfig.class, JpaLoggerConfig.class })
@ActiveProfiles(value = { "mapUser", "logger", "dev" })
@Transactional
@Slf4j
public class MapKeyTest {

    @Autowired
    private EntityManager em;
    
    @Autowired
    private UserMapRepo userMapRepo;

    /*@ElementCollection
    @CollectionTable(name = "MAP_PHONES")
    @MapKeyColumn(name = "PHONE_NUM")
    @Column(name = "NUM")
    private Map<String, String> phones = new HashMap<>();
    private String name;*/
    
    
    @Test
    public void shouldRetrieveMapValues() {
        Map<String,String> phones = Maps.newHashMap();
        phones.put("slawek", "232323");
        phones.put("tomek", "27773");
        UserMap um = UserMap.builder().name("przodownik").phones(phones).build();
        userMapRepo.save(um);
        UserMap umLoaded = userMapRepo.findOne(1l);
        umLoaded.getPhones().entrySet().stream().forEach(entry->log.info(" key : {}, value : {}",entry.getKey(),entry.getValue()));        
        
    }

    
    
}
