package pl.java.scalatech.collection.map.collection;

import java.util.Map;

import javax.persistence.EntityManager;

import org.junit.Ignore;
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
import pl.java.scalatech.domain.mapkey.basic_collection.Book;
import pl.java.scalatech.domain.mapkey.basic_collection.BookStore;
import pl.java.scalatech.repository.map.collection.BookStoreRepo;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { PropertiesLoader.class, BookStoreMapCollectionConfig.class, JpaLoggerConfig.class })
@ActiveProfiles(value = { "mapCollection", "logger", "dev" })
@Transactional
@Slf4j
public class MapCollectionTest {

    @Autowired
    private EntityManager em;
    
    @Autowired
    private BookStoreRepo bookStoreRepo;

    @Test
    @Ignore //TODO
    public void shouldBootstrap() {
        Map<Book,Long> invertory = Maps.newHashMap();
        invertory.put(Book.builder().name("quo vadis").isbn("232f").build(), 1l);
        invertory.put(Book.builder().name("lalka").isbn("23244f").build(), 2l);
        BookStore bs = BookStore.builder().address("poznan,pulawska 20").invertory(invertory).build();
        bookStoreRepo.save(bs);
        
        log.info("{}",bookStoreRepo.findAll());
    }

    
    
}
