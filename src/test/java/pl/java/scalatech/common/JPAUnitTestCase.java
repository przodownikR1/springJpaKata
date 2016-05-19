package pl.java.scalatech.common;

import static org.junit.runners.MethodSorters.NAME_ASCENDING;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
@FixMethodOrder(NAME_ASCENDING)
public abstract class JPAUnitTestCase {

    protected EntityManagerFactory entityManagerFactory;

    @Before
    public void init() {
        entityManagerFactory = Persistence.createEntityManagerFactory( "PU" );

    }

    @After
    public void destroy() {
        entityManagerFactory.close();
    }



}