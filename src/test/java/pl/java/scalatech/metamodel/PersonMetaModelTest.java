package pl.java.scalatech.metamodel;

import java.util.Set;

import javax.persistence.EntityManagerFactory;
import javax.persistence.metamodel.ManagedType;
import javax.persistence.metamodel.Metamodel;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lombok.extern.slf4j.Slf4j;
import pl.java.scalatech.config.TestJpaConfig;

@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { TestJpaConfig.class })
@ActiveProfiles("logger")
public class PersonMetaModelTest {

    @Autowired
    private EntityManagerFactory emf;

    @Test
    public void shouldBootstrap() {

    }

    @Test
    public void shouldMetaModelWork() {
        Metamodel mm = emf.getMetamodel();
        Set<ManagedType<?>> managedTypes = mm.getManagedTypes();
        for(ManagedType<?> mType: managedTypes){
            log.info("{},{}",mType.getJavaType(),mType.getPersistenceType());
        }

    }

}