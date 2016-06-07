package pl.java.scalatech.one2one.joinTable;

import static org.junit.runners.MethodSorters.NAME_ASCENDING;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.FixMethodOrder;
import org.junit.Test;

import lombok.extern.slf4j.Slf4j;
import pl.java.scalatech.domain.oneToOne.joinTable.Address;
import pl.java.scalatech.domain.oneToOne.joinTable.User;




@Slf4j
@FixMethodOrder(NAME_ASCENDING)
public class One2OneJoinTableTest extends ORMStandaloneClassTestCase{
    @Test
    public void should_A_SAVE() {
        try(Session session = sf.openSession()){
            Transaction tx = session.beginTransaction();
            Address address = Address.builder().street("aleje").build();
            User user = User.builder().address(address).login("przodownik").build();
            session.save(user);

            tx.commit();
        } 
    }

    @Test
    public void should_B_LOAD() {
        try(Session session = sf.openSession()){
            List<User> result =  session.createQuery("FROM "+User.class.getSimpleName()).list();
            log.info("{}",result);

        } 
    }
}
