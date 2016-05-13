package pl.java.scalatech.exercise.inheritence.joined;

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
import pl.java.scalatech.domain.inheritence.joined.CreditAccountJoin;
import pl.java.scalatech.domain.inheritence.joined.DebitAccountJoin;
import pl.java.scalatech.repository.inheritence.joined.AccountRepo;
import pl.java.scalatech.repository.inheritence.joined.CreditJoinRepo;
import pl.java.scalatech.repository.inheritence.joined.DebitJoinRepo;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { PropertiesLoader.class, JoinedTableConfig.class })
@ActiveProfiles(value = "joined")
@Transactional
@Slf4j
public class JoinedTableTest {

    @Autowired
    private AccountRepo repo;

    @Autowired
    private CreditJoinRepo creditRepo;

    @Autowired
    private DebitJoinRepo debitRepo;



    @Test
    public void shouldWork(){
        repo.save(new CreditAccountJoin(1l,"slawek",BigDecimal.valueOf(12),BigDecimal.valueOf(23),BigDecimal.valueOf(35)));
        repo.save(new CreditAccountJoin(2l,"kalina",BigDecimal.valueOf(48),BigDecimal.valueOf(78),BigDecimal.valueOf(45)));
        repo.save(new DebitAccountJoin(3l,"aga",BigDecimal.valueOf(62),BigDecimal.valueOf(83),BigDecimal.valueOf(95)));

        Assertions.assertThat(creditRepo.count()).isEqualTo(2);
        Assertions.assertThat(debitRepo.count()).isEqualTo(1);
        Assertions.assertThat(repo.count()).isEqualTo(3);


    }
}
