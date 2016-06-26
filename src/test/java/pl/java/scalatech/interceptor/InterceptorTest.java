package pl.java.scalatech.interceptor;

import static org.junit.runners.MethodSorters.NAME_ASCENDING;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.FixMethodOrder;
import org.junit.Test;

import lombok.extern.slf4j.Slf4j;
import pl.java.scalatech.domain.audit.Book;

@Slf4j
@FixMethodOrder(NAME_ASCENDING)
public class InterceptorTest {

    @Test
    public void should_A_SAVE() {
        Session session = HibernateUtils.getSessionFactory().withOptions().interceptor(new AuditableInterceptor()).openSession();
        try {
            Transaction tx = session.beginTransaction();
            Book book = new Book();
            book.setName("quovadis");
            session.save(book);

            tx.commit();
        } finally {
            session.close();
        }
    }

    
    @Test
    public void should_B_GET() {
        Session session = HibernateUtils.getSessionFactory().openSession();
        try {
            Transaction tx = session.beginTransaction();
            Book loaded = session.get(Book.class,1l);
            log.info("{}",loaded);

            tx.commit();
        } finally {
            session.close();
        }
    }

}
