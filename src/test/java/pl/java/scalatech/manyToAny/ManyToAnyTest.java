package pl.java.scalatech.manyToAny;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.slf4j.Slf4j;
import pl.java.scalatech.config.PropertiesLoader;
import pl.java.scalatech.domain.manyToAny.LongProperty;
import pl.java.scalatech.domain.manyToAny.PropertyMap;
import pl.java.scalatech.domain.manyToAny.StringProperty;
import pl.java.scalatech.utils.HibernateUtils;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {  PropertiesLoader.class,ManyToAnyConfig.class })
@ActiveProfiles(value = {"manyToAny","logger","dev"})
@Transactional
@Slf4j
public class ManyToAnyTest {

    @Test
    @Ignore
    public void testManyToAnyWithMap() throws Exception {

        Session s = HibernateUtils.getSessionFactory().openSession();
      
        Transaction t = s.beginTransaction();

        PropertyMap map = new PropertyMap( "sample" );
        map.getProperties().add( new StringProperty( "name", "Alex" ) );
        map.getProperties().add(new LongProperty( "age", 33l ) );

        s.save( map );

        s.flush();
        s.clear();

        Query q = s.createQuery( "SELECT map FROM PropertyMap " );
       
        List<PropertyMap> actualMap = q.list();
        log.info("{}",actualMap);
        
        t.rollback();
        s.close();

    }

}
