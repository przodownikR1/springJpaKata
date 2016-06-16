package pl.java.scalatech.manyToAny;

import static org.assertj.core.api.Assertions.assertThat;

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

import pl.java.scalatech.config.PropertiesLoader;
import pl.java.scalatech.domain.manyToAny.LongProperty;
import pl.java.scalatech.domain.manyToAny.Property;
import pl.java.scalatech.domain.manyToAny.PropertyMap;
import pl.java.scalatech.domain.manyToAny.StringProperty;
import pl.java.scalatech.utils.HibernateUtils;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {  PropertiesLoader.class,ManyToAnyConfig.class })
@ActiveProfiles(value = "manyToAny")
@Transactional
public class ManyToAnyTest {

    @Test
    @Ignore//TODO
    public void testManyToAnyWithMap() throws Exception {

        Session s = HibernateUtils.getSessionFactory().openSession();
        s.doWork(connection -> {


        });
        Transaction t = s.beginTransaction();

        PropertyMap map = new PropertyMap( "sample" );
        map.getProperties().put( "name", new StringProperty( "name", "Alex" ) );
        map.getProperties().put( "age", new LongProperty( "age", 33l ) );

        s.save( map );

        s.flush();
        s.clear();

        Query q = s.createQuery( "SELECT map FROM PropertyMap map WHERE map.name = :name" );
        q.setString( "name", "sample" );
        PropertyMap actualMap = (PropertyMap) q.uniqueResult();

        assertThat( actualMap ).isNotNull();
        assertThat( actualMap.getProperties()).isNotNull();

        Property property = actualMap.getProperties().get( "name" );
        assertThat( property ).isNotNull();

        assertThat(property.asString()).isEqualTo("Alex");

        property = actualMap.getProperties().get( "age" );
        assertThat( property ).isNotNull();
        assertThat(property).isInstanceOf(LongProperty.class);
        assertThat(property.asString()).isEqualTo(33l);

        t.rollback();
        s.close();

    }

}
