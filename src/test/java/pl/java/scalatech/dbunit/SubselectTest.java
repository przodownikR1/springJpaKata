package pl.java.scalatech.dbunit;

import java.util.List;

import org.junit.Test;

import com.github.springtestdbunit.annotation.DatabaseSetup;

import pl.java.scalatech.dbunit.entity.Client;

@DatabaseSetup("/nplusone.xml")
public class SubselectTest extends BaseTest {
    @Test
    public void subSelect() {
        //noinspection unchecked
        List<Client> clients = getSession().createQuery("select c from Client c " +
                "where c.age >= :age")
                .setParameter("age", 18)
                .list();
        clients.forEach(c -> c.getAccounts().size());
    }
}