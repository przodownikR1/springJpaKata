package pl.java.scalatech.one2many;

import static org.junit.runners.MethodSorters.NAME_ASCENDING;

import org.junit.FixMethodOrder;
import org.junit.Test;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@FixMethodOrder(NAME_ASCENDING)
public class One2ManyTest extends ORMStandaloneTestCase{

    @Override
    protected Class<?> getEntityClass() {
        return null;
    }

    @Override
    protected String packageBase() {
        return "pl.java.scalatech.domain.oneToMany.normal";
    }

    @Test
    public void shouldBootstrapTest(){

    }

}
