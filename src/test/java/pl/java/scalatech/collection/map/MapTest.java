package pl.java.scalatech.collection.map;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;

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
import pl.java.scalatech.config.PropertiesLoader;
import pl.java.scalatech.domain.mapping.map.Account;
import pl.java.scalatech.repository.mapping.MapRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { PropertiesLoader.class, JpaMapConfig.class ,JpaLoggerConfig.class})
@ActiveProfiles(value = {"map","logger"})
@Transactional
@Slf4j
public class MapTest {

    @Autowired
    private EntityManager em;
    @Autowired
    private MapRepository repo;

    @Test
    public void shouldBootstrap(){
           Map<String,String> map = new HashMap<>();
           map.put("a", "bb");
           map.put("c", "dd");
           List<String> mails =Lists.newArrayList("a@a.pl","c@c.pl");
           Account account = new Account(map,mails);
           repo.save(account);

           log.info("{}",em.find(Account.class, 1l));
    }

}
