package pl.java.scalatech.spring;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { TestJSimpleConfig.class })
public class DataSourceTest {

    @Autowired
    private javax.sql.DataSource dataSource;

    @Test
    public void shouldConnectionWork() {

        Assertions.assertThat(dataSource).isNotNull();

        try {
            Connection connection = dataSource.getConnection();
            ResultSet rs = connection.createStatement().executeQuery("SELECT 1");
            if (rs.first()) {
                Assertions.assertThat(rs.first()).isTrue();
            } else {
                Assertions.assertThat(false);
            }
            connection.close();
            Assertions.assertThat(false);
        } catch (SQLException e) {
            log.info("{}",e);
            Assertions.assertThat(false);

        }
    }
}
