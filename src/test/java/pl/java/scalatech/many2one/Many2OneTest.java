package pl.java.scalatech.many2one;

import static org.junit.runners.MethodSorters.NAME_ASCENDING;

import org.junit.FixMethodOrder;
import org.junit.Test;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@FixMethodOrder(NAME_ASCENDING)
public class Many2OneTest extends ORMStandaloneTestCase{

    @Override
    protected Class<?> getEntityClass() {
        return null;
    }

    @Override
    protected String packageBase() {
        return "pl.java.scalatech.domain.manyToOne";
    }
    @Test
    public void shouldBootstrapTest(){

    }
}
