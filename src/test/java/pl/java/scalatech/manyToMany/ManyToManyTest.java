package pl.java.scalatech.manyToMany;

import static org.junit.runners.MethodSorters.NAME_ASCENDING;

import java.util.List;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.FixMethodOrder;
import org.junit.Test;

import com.google.common.collect.Sets;

import lombok.extern.slf4j.Slf4j;
import pl.java.scalatech.common.ORMStandaloneTestCase;
import pl.java.scalatech.domain.manyToMany.Crew;
import pl.java.scalatech.domain.manyToMany.Tank;

@Slf4j
@FixMethodOrder(NAME_ASCENDING)
public class ManyToManyTest extends ORMStandaloneTestCase {

    @Test
    public void should_A_Save_TANK() {
        Session session = sf.openSession();
        try {
            Transaction tx = session.beginTransaction();
            Set<Crew> crews = Sets.newHashSet(Crew.builder().count(2).build(), Crew.builder().count(5).build());
            Tank tank = Tank.builder().name("t-34").crews(crews).build();
            session.save(tank);

            Crew crew = Crew.builder().count(2).build();
            session.save(crew);
            tx.commit();
        } catch (Exception e) {
            log.error("{}", e);
        } finally {
            session.close();
        }
    }

    @Test
    public void should_B_RETRIEVE_TANK() {
        Session session = sf.openSession();
        try {
            Transaction tx = session.beginTransaction();

            List<Tank> tanks = session.createQuery("FROM Tank").list();
            List<Crew> crews = session.createQuery("FROM Crew").list();
            log.info("{}",tanks);
            log.info("{}",crews);
            tx.commit();
        } catch (Exception e) {
            log.error("{}", e);
        } finally {
            session.close();
        }
    }

    @Override
    protected Class<?> getEntityClass() {
        return null;
    }

    @Override
    protected String packageBase() {
        return "pl.java.scalatech.domain.manyToMany";
    }

}
