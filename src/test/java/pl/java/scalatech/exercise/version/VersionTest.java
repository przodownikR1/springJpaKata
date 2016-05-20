package pl.java.scalatech.exercise.version;

import static org.junit.runners.MethodSorters.NAME_ASCENDING;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.FixMethodOrder;
import org.junit.Test;

import lombok.extern.slf4j.Slf4j;
import pl.java.scalatech.domain.version.Book;


@Slf4j
@FixMethodOrder(NAME_ASCENDING)
public class VersionTest extends ORMStandaloneClassTestCase{
    @Test
    public void should_A_SAVE() {
        Session session = sf.openSession();
        try {
            Transaction tx = session.beginTransaction();
            Book b = Book.builder().name("quo vadis").build();
            session.save(b);

            tx.commit();
        } catch (Exception e) {
            log.error("{}", e);
        } finally {
            session.close();
        }
    }

    @Test
    public void should_B_VersionWork() {
        Session session = sf.openSession();
        try {
            Transaction tx = session.beginTransaction();
            Book loaded = session.get(Book.class, 1l);
            log.info("loaded {}", loaded);
            loaded.setName("krzyzacy");
            session.flush();
            log.info("loaded 2 {}", loaded);
            tx.commit();
        } catch (Exception e) {
            log.error("{}", e);
        } finally {
            session.close();
        }

    }

    @Test
    public void should_C_VersionWork() {
        Session session = sf.openSession();
        try {
            Transaction tx = session.beginTransaction();
            log.info("END : {}", session.load(Book.class, 1l));
            tx.commit();
        } catch (Exception e) {
            log.error("{}", e);
        } finally {
            session.close();
        }
    }

}
