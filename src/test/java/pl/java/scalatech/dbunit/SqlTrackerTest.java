package pl.java.scalatech.dbunit;

import org.junit.Test;

import com.github.springtestdbunit.annotation.DatabaseSetup;

import pl.java.scalatech.dbunit.entity.Account;
import pl.java.scalatech.dbunit.entity.Client;

@DatabaseSetup("/sqltracker.xml")
public class SqlTrackerTest extends BaseTest {

    @Test
    public void showStatistics() {
        Client client = getSession().get(Client.class, 1);
    }

    @Test
    public void sqlCountAssertion() {
        AssertSqlCount.reset();
        Account account1 = getSession().get(Account.class, 1);
        Account account2 = getSession().get(Account.class, 2);
        AssertSqlCount.assertSelectCount(1);
    }
}