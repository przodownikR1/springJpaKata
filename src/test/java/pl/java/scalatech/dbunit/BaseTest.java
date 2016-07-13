package pl.java.scalatech.dbunit;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.annotation.IfProfileValue;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.transaction.AfterTransaction;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;

import com.github.springtestdbunit.DbUnitTestExecutionListener;

import lombok.extern.slf4j.Slf4j;

import static pl.java.scalatech.dbunit.QueryCountInfoHolder.getQueryInfo;
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = DbUnitConfig.class)
@TestExecutionListeners({
        TransactionalTestExecutionListener.class,
        DependencyInjectionTestExecutionListener.class,
        DbUnitTestExecutionListener.class
})
@IfProfileValue(name = "run-tests", value = "true")
@Transactional
@ActiveProfiles("dbunit")
@Slf4j
public abstract class BaseTest {

    private static final String[] DB_UNIT_SET_UP = {"",
            "****************************************************************",
            "*************** DATABASE HAS BEEN ALREADY SET UP ***************",
            "****************************************************************"
    };

    @Before
    public void dbAllSet() {
        for (String line : DB_UNIT_SET_UP) {
        log.info("{}",line);
        }
       
    }

    @AfterTransaction
    public void showSqlCount() {
        log.info("\nSql count: {}" , getQueryInfo().countAll());
    }

    @PersistenceContext
    protected EntityManager em;

    protected Session getSession() {
        return em.unwrap(Session.class);
    }

    protected SessionFactory getSessionFactory() {
        return getSession().getSessionFactory();
    }
}