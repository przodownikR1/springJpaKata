package pl.java.scalatech.exercise.inheritence.mapped;

import java.math.BigDecimal;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.slf4j.Slf4j;
import pl.java.scalatech.config.PropertiesLoader;
import pl.java.scalatech.domain.inheritence.mapped.CreditAccount;
import pl.java.scalatech.domain.inheritence.mapped.DebitAccount;
import pl.java.scalatech.repository.inheritence.mapped.CreditAccoutRepo;
import pl.java.scalatech.repository.inheritence.mapped.DebitAccountRepo;



@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { PropertiesLoader.class, MappedTableConfig.class })
@ActiveProfiles(value = {"mapped","logger","dev"})
@Transactional
@Slf4j
public class MappedTableTest {



    @Autowired
    private CreditAccoutRepo creditRepo;

    @Autowired
    private DebitAccountRepo debitRepo;



    @Test

    public void shouldWork(){
    //    creditRepo.save(new CreditAccount("slawek",BigDecimal.valueOf(23),BigDecimal.valueOf(35)));
        creditRepo.save(new CreditAccount("kalina",BigDecimal.valueOf(48),BigDecimal.valueOf(78)));
        debitRepo.save(new DebitAccount("aga",BigDecimal.valueOf(62),BigDecimal.valueOf(95)));

        Assertions.assertThat(creditRepo.count()).isEqualTo(1);
        Assertions.assertThat(debitRepo.count()).isEqualTo(1);



    }
}
