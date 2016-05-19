package pl.java.scalatech.common;

import static org.junit.runners.MethodSorters.NAME_ASCENDING;

import org.assertj.core.api.Assertions;
import org.hibernate.SessionFactory;
import org.junit.FixMethodOrder;
import org.junit.Test;

import pl.java.scalatech.utils.HibernateServiceUtils;

@FixMethodOrder(NAME_ASCENDING)
public class AbstractHibTest {
    protected final SessionFactory sf = HibernateServiceUtils.getSessionFactory();

    @Test
    public void should_A_SessionFactoryCreate() {
        Assertions.assertThat(sf).isNotNull();
    }
}
