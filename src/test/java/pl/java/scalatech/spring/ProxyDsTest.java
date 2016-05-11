package pl.java.scalatech.spring;

import java.sql.PreparedStatement;
import java.util.Arrays;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { ProxyDsConfig.class })
public class ProxyDsTest {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Test
    @SneakyThrows
    public void test(){
        log.info("**********************************************************");

        jdbcTemplate.execute("CREATE TABLE users (id INT, name VARCHAR(20))");

        jdbcTemplate.batchUpdate("INSERT INTO users (id, name) VALUES (?, ?)", Arrays.asList(new Object[][]{{1, "foo"}, {2, "bar"}}));

        PreparedStatement preparedStatement = jdbcTemplate.getDataSource().getConnection().prepareStatement("INSERT INTO users (id, name) VALUES (?, ?)");
        preparedStatement.setString(2, "FOO");
        preparedStatement.setInt(1, 3);
        preparedStatement.addBatch();
        preparedStatement.setInt(1, 4);
        preparedStatement.setString(2, "BAR");
        preparedStatement.addBatch();
        preparedStatement.executeBatch();

        jdbcTemplate.queryForObject("SELECT COUNT(*) FROM users", Integer.class);
        log.info("**********************************************************");

    }


}
