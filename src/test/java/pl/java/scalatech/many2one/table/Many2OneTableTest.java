package pl.java.scalatech.many2one.table;

import static org.junit.runners.MethodSorters.NAME_ASCENDING;

import org.junit.FixMethodOrder;
import org.junit.Test;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@FixMethodOrder(NAME_ASCENDING)
public class Many2OneTableTest extends ORMStandaloneMany2OneTestCase{

    @Override
    protected Class<?> getEntityClass() {
        return null;
    }

    @Override
    protected String packageBase() {
        return "pl.java.scalatech.domain.manyToOne.table";
    }
    @Test
    public void shouldBootstrapTest(){

    }
}
