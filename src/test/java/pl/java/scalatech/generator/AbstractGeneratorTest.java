
package pl.java.scalatech.generator;

import static org.junit.runners.MethodSorters.NAME_ASCENDING;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.FixMethodOrder;
import org.junit.Test;

import lombok.extern.slf4j.Slf4j;
import pl.java.scalatech.common.AbstractHibTest;

@Slf4j
@FixMethodOrder(NAME_ASCENDING)
public abstract class AbstractGeneratorTest extends AbstractHibTest{

    @Test
    public void should_B_CreateAndSaveGeneratorOneEntity() {
        log.info("+++  save finally Entity");
        Session session = sf.openSession();
        try {
            Transaction tx = session.beginTransaction();
            for (int i = 0; i < 10; i++) {
                session.save(createEntity(i));
            }
            tx.commit();
        } catch (Exception e) {
            log.error("{}", e);
        } finally {
            session.close();
        }
    }

    @Test
    public void should_C_LoadGeneratorOneEntity() {
        log.info("+++  load Entity");
        Session session = sf.openSession();
        Query query = session.createQuery("FROM " + getEntityName());
        query.list().forEach(i -> log.info("{}", i));
        session.close();
    }

    abstract String getEntityName();

    abstract Object createEntity(int i);

}
