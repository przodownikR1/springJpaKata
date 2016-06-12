package pl.java.scalatech.firstCache;

import java.math.BigDecimal;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;

import lombok.extern.slf4j.Slf4j;
import pl.java.scalatech.common.AbstractHibTest;
import pl.java.scalatech.domain.Item;
@Slf4j
public class FirstLevelCacheTest extends  AbstractHibTest{

    @Test
    public void should_A_CreateAndSaveItemEntity() {
        log.info("+++  save item entity");
        Session session = sf.openSession();
        try {
            Transaction tx = session.beginTransaction();
            for (int i = 0; i < 10; i++) {
                session.save(Item.builder().name("_"+i).price(BigDecimal.valueOf(i)).build());
            }
            tx.commit();
        } catch (Exception e) {
            log.error("{}", e);
        } finally {
            session.close();
        }
    }
    @Test
    public void should_B_retrieveItemFirstLevelCache() {
        log.info("retrieveItemFirstLevelCache ================================");
        try(Session session = sf.openSession()){
            Item one = session.get(Item.class, 1l);
            log.info("one : {}",one);
            Item oneNext = session.get(Item.class, 1l);
            log.info("oneNext : {}",one);
        }
        log.info("retrieveItemFirstLevelCache ================================");

    }
    @Test
    public void should_C_retrieveSeparateSessionItemFirstLevelCache() {
        log.info("retrieveSeparateItemFirstLevelCache ================================");
        try(Session session = sf.openSession()){
            Item one = session.get(Item.class, 1l);
            log.info("oneSeparate : {}",one);
            Item oneNext = session.get(Item.class, 1l);
            log.info("oneNextSeparate : {}",one);
        }
        try(Session session = sf.openSession()){
            Item one = session.get(Item.class, 1l);
            log.info("oneSeparate : {}",one);
            Item oneNext = session.get(Item.class, 1l);
            log.info("oneNextSeparate : {}",one);
        }

        log.info("retrieveSeparateItemFirstLevelCache ================================");
    }

}
