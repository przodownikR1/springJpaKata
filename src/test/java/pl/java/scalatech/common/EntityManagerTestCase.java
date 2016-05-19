package pl.java.scalatech.common;

import static org.junit.runners.MethodSorters.NAME_ASCENDING;

import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;

import lombok.extern.slf4j.Slf4j;

@FixMethodOrder(NAME_ASCENDING)
@Slf4j
public abstract class EntityManagerTestCase {

    private static final String UNIT_NAME = "PU";

    private EntityManagerFactory factory;
    private EntityManager manager;

    public EntityManager getManager() {
        return manager;
    }

    protected abstract Map<String,Object> getOverrideProps();

    @Before
    public void openFactory() throws Exception {

        factory = Persistence.createEntityManagerFactory(UNIT_NAME, getOverrideProps());
        log.info("+++ create factory and entityManager and open transation");
        manager = factory.createEntityManager();
        manager.getTransaction().begin();
    }

    @After
    public void commitTransactionAndCloseManager() throws Exception {

        if (manager.getTransaction().isActive()) {
            manager.getTransaction().commit();
        }

        if (manager.isOpen()) {
            manager.close();
        }
        log.info("+++ commit transaction , close entityManager and entityManagerFactory");
        factory.close();
    }
}