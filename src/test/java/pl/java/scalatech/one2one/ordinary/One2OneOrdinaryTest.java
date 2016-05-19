package pl.java.scalatech.one2one.ordinary;

import static org.junit.runners.MethodSorters.NAME_ASCENDING;

import java.time.LocalDateTime;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.FixMethodOrder;
import org.junit.Test;

import lombok.extern.slf4j.Slf4j;
import pl.java.scalatech.domain.oneToOne.Email;
import pl.java.scalatech.domain.oneToOne.ordinary.EmailMessage;

@Slf4j
@FixMethodOrder(NAME_ASCENDING)
public class One2OneOrdinaryTest extends ORMStandaloneClassTestCase{


    @Test
    public void should_A_SAVE() {
        Session session = sf.openSession();
        try {
            Transaction tx = session.beginTransaction();
            Email em = Email.builder().from("przodownik@tlen.pl").time(LocalDateTime.now()).build();
            EmailMessage message = new EmailMessage("this is test",em);
            session.save(message);

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
            List<EmailMessage> result =  session.createQuery("FROM "+EmailMessage.class.getSimpleName()).list();
            log.info("{}",result);

        } catch (Exception e) {
            log.error("{}", e);
        } finally {
            session.close();
        }
    }


}
