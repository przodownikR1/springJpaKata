package pl.java.scalatech.common;

import static org.junit.runners.MethodSorters.NAME_ASCENDING;

import org.assertj.core.api.Assertions;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.FixMethodOrder;
import org.junit.Test;

import lombok.extern.slf4j.Slf4j;
import pl.java.scalatech.utils.HibernateServiceUtils;

@FixMethodOrder(NAME_ASCENDING)
@Slf4j
public abstract class AbstractConcreteClazzHibTest {
    protected final SessionFactory sf = HibernateServiceUtils.getSessionFactory(getEntities());

    @Test
    public void should_A_SessionFactoryCreate() {
        Assertions.assertThat(sf).isNotNull();
    }

    public abstract Class<?>[] getEntities();

    public abstract Object createEntity(int i);
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
        Query query = session.createQuery("FROM " +getEntities()[0].getSimpleName());
        query.list().forEach(i -> log.info("{}", i));
        session.close();
    }

}
