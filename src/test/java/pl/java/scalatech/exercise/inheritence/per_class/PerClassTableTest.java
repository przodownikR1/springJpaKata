package pl.java.scalatech.exercise.inheritence.per_class;

import org.assertj.core.api.Assertions;
import org.javamoney.moneta.Money;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.slf4j.Slf4j;
import pl.java.scalatech.config.PropertiesLoader;
import pl.java.scalatech.domain.inheritence.per_class.DetailProduct;
import pl.java.scalatech.domain.inheritence.per_class.StockProduct;
import pl.java.scalatech.repository.inheritence.per_table.DetailProductRepo;
import pl.java.scalatech.repository.inheritence.per_table.ProductRepo;
import pl.java.scalatech.repository.inheritence.per_table.StockProductRepo;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { PropertiesLoader.class, PerClassTableConfig.class })
@ActiveProfiles(value = "per_class")
@Transactional
@Slf4j
public class PerClassTableTest {

    @Autowired
    private ProductRepo productRepo;

    @Autowired
    private DetailProductRepo detailProductRepo;

    @Autowired
    private StockProductRepo stockProductRepo;
   //tag::main[]
    @Test
    public void shouldWork(){
        detailProductRepo.save(new DetailProduct("pl", "knife", Money.of(23, "PLN")));
        detailProductRepo.save(new DetailProduct("pl", "knife", Money.of(23, "PLN")));
        stockProductRepo.save(new StockProduct("discount","fork","USD"));
        Assertions.assertThat(detailProductRepo.count()).isEqualTo(2);//<1>
        Assertions.assertThat(stockProductRepo.count()).isEqualTo(1);//<2>
        Assertions.assertThat(productRepo.count()).isEqualTo(3);//<3>
    }
    // end::main[]
}
