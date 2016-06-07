package pl.java.scalatech.filter;

import static org.junit.runners.MethodSorters.NAME_ASCENDING;

import org.assertj.core.api.Assertions;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.junit.FixMethodOrder;
import org.junit.Test;

import lombok.extern.slf4j.Slf4j;
import pl.java.scalatech.common.ORMStandaloneTestCase;
import pl.java.scalatech.common.SqlDataAccount;
import pl.java.scalatech.domain.filter.Pupil;

@SqlDataAccount
@Slf4j
@FixMethodOrder(NAME_ASCENDING)
public class FilterTest extends ORMStandaloneTestCase{


    @Test
    public void shouldBootstrapTest(){
        Assertions.assertThat(sf).isNotNull();
    }
    @Test
    public void shouldRetrieveDataInNormalWay(){
        try(Session session = sf.openSession()){
            Transaction tx = session.beginTransaction();
            Criteria criteria = session.createCriteria(Pupil.class).addOrder(Order.asc("name"));

            log.info("{}",criteria.list());

            tx.commit();
        }
    }


    @Override
    protected Class<?> getEntityClass() {
        return Pupil.class;
    }
    @Override
    protected String packageBase() {
        return null;
    }

}
