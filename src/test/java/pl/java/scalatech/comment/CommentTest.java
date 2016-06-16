package pl.java.scalatech.comment;

import static org.junit.runners.MethodSorters.NAME_ASCENDING;

import org.assertj.core.api.Assertions;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.FixMethodOrder;
import org.junit.Test;

import lombok.extern.slf4j.Slf4j;
import pl.java.scalatech.common.ORMStandaloneTestCase;
import pl.java.scalatech.domain.bags.Item;
@Slf4j
@FixMethodOrder(NAME_ASCENDING)
public class CommentTest extends ORMStandaloneTestCase{

    @Test
    public void shouldBootstrapTest(){
        Assertions.assertThat(sf).isNotNull();
    }
    @Test
    public void shouldRetrieveDataInNormalWay(){
        try(Session session = sf.openSession()){
            Transaction tx = session.beginTransaction();
            //TODO
          /*  Query query  = session.getNamedQuery("selectAllItem");
            List<Item> items = query.list();
            log.info("{}",items);*/

            tx.commit();
        }
    }

    @Override
    protected Class<?> getEntityClass() {
        return Item.class;
    }

    @Override
    protected String packageBase() {
        return null;
    }

}
