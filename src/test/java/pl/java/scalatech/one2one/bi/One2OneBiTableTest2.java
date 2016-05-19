package pl.java.scalatech.one2one.bi;

import static org.junit.runners.MethodSorters.NAME_ASCENDING;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.FixMethodOrder;
import org.junit.Test;

import lombok.extern.slf4j.Slf4j;
import pl.java.scalatech.domain.oneToOne.bi.Address;
import pl.java.scalatech.domain.oneToOne.bi.User;





@Slf4j
@FixMethodOrder(NAME_ASCENDING)
public class One2OneBiTableTest2 extends ORMStandaloneClassTestCase{
    @Test
    public void should_A_SAVE() {
        Session session = sf.openSession();
        try {
            Transaction tx = session.beginTransaction();
            User user = User.builder().login("przodownik").build();
            Address address = Address.builder().street("aleje").user(user).build();

            session.save(address);

            tx.commit();
        } catch (Exception e) {
            log.error("{}", e);
        } finally {
            session.close();
        }
    }

    @Test
    public void should_B_LOAD() {
        Session session = sf.openSession();
        try {
            List<User> result =  session.createQuery("FROM "+User.class.getSimpleName()).list();
            log.info("user {}",result);
            log.info("address [0] {}",result.get(0).getAddress());

        } catch (Exception e) {
            log.error("{}", e);
        } finally {
            session.close();
        }
    }
}
