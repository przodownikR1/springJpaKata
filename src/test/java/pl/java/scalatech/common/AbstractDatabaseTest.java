package pl.java.scalatech.common;

import org.springframework.test.context.jdbc.Sql;

@Sql(scripts = {"classpath:data/test_data.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(scripts = {"classpath:data/drop_database.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
public abstract class AbstractDatabaseTest {
}
