package pl.java.scalatech.manyToMany;

import static org.junit.runners.MethodSorters.NAME_ASCENDING;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.FixMethodOrder;
import org.junit.Test;

import com.google.common.collect.Sets;

import lombok.extern.slf4j.Slf4j;
import pl.java.scalatech.common.ORMStandaloneTestCase;
import pl.java.scalatech.domain.manyToMany.CrewBi;
import pl.java.scalatech.domain.manyToMany.TankBi;

@Slf4j
@FixMethodOrder(NAME_ASCENDING)
public class ManyToManyTestBi extends ORMStandaloneTestCase {

    @Test

    public void should_A_Save_TANK() {
        Session session = sf.openSession();
        try {
            Transaction tx = session.beginTransaction();

            TankBi tank = TankBi.builder().name("t-34").crews(Sets.newHashSet()).build();
            tank.addCrew(CrewBi.builder().count(2).tanks(Sets.newHashSet()).build());
           tank.addCrew(CrewBi.builder().count(5).tanks(Sets.newHashSet()).build());

            session.save(tank);
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

            List<TankBi> tank = session.createQuery("FROM TankBi").list();
            log.info("{}",tank);
            log.info("crews : {} ",tank.get(0).getCrews());
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
