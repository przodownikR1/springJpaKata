package pl.java.scalatech.sql;

import static com.google.common.collect.Sets.newHashSet;

import java.util.Set;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import pl.java.scalatech.config.PropertiesLoader;
import pl.java.scalatech.domain.sql.Part;
import pl.java.scalatech.domain.sql.Weapon;
import pl.java.scalatech.repository.sql.WeaponRepo;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {  PropertiesLoader.class,WeaponConfig.class })
@ActiveProfiles(value = "weapon")
@Transactional
public class WeaponTest {
    @Autowired
    private WeaponRepo repo;
    @Autowired
    private EntityManager em;
    @Test
    public void shouldBootstap(){

        Set<Part> parts = newHashSet();
        parts.add(Part.builder().active(true).name("trigger").build());
        Weapon weapon = new Weapon(1l, "gun", "bereta", parts);
        Session session = em.unwrap(Session.class);
        try{

        Transaction transaction = session.beginTransaction();
        session.save(weapon);
        transaction.commit();
        }finally
        {
            session.close();
        }



    }

}
