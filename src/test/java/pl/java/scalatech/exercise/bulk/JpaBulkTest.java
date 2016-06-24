package pl.java.scalatech.exercise.bulk;

import java.util.Random;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.Root;

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
import pl.java.scalatech.domain.bulk.Address;
import pl.java.scalatech.repository.bulk.AddressRepo;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { PropertiesLoader.class, JpaBulkConfig.class })
@ActiveProfiles(value = "bulk")
@Transactional
@Slf4j
@SqlDataAccount
public class JpaBulkTest {

    @Autowired
    private AddressRepo addressRepo;

    @Autowired
    private EntityManager em;

    @Test
    public void shouldBootstrap() {
        Assertions.assertThat(addressRepo.count()).isEqualTo(30);
        Assertions.assertThat(addressRepo.findByDisableIsNull()).hasSize(30);
    }
  //tag::main[]
    @Test
    public void shouldBulkUpdate() {
        Query query = em.createQuery("update Address a set a.disable = true where a.id > :id").setParameter("id", 0l); //<1>
        int updatedEntities = query.executeUpdate(); //<2>
        Assertions.assertThat(updatedEntities).isEqualTo(30);
        Assertions.assertThat(addressRepo.findByDisableTrue()).hasSize(30);
    }

    @Test
    public void shouldBulkUpdateCriteria() {
        CriteriaBuilder cb = em.getCriteriaBuilder(); 
        CriteriaUpdate<Address> update = cb.createCriteriaUpdate(Address.class); //<3>
        Root<Address> i = update.from(Address.class);
        update.set(i.get("disable"), true);
        update.where(cb.greaterThan(i.get("id"), 0l));
        int updatedEntities = em.createQuery(update).executeUpdate(); //<4>
        Assertions.assertThat(updatedEntities).isEqualTo(30);
        Assertions.assertThat(addressRepo.findByDisableTrue()).hasSize(30);
    }

    @Test  // jpql em.createQuery("delete Address a where a.city like 'w%'").executeUpdate();
    public void shouldBulkDeleteCriteria() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaDelete<Address> delete = cb.createCriteriaDelete(Address.class); //<5>
        Root<Address> from = delete.from(Address.class);
        delete.where(cb.like(from.get("city"), "w%"));
        int deleted = em.createQuery(delete).executeUpdate(); //<6>
        Assertions.assertThat(deleted).isEqualTo(6);
    }

    @Test
    public void shouldInsertDataInBulkWay() {
        int deleted = em.createQuery("delete Address a where a.id > 0").executeUpdate(); //<7>
        Assertions.assertThat(deleted).isEqualTo(30);
        for (long i = 0; i < 100; i++) {
            Address address = Address.builder().city("city_" + i).disable(new Random().nextBoolean()).build();
            log.info("address {}",address);
            em.persist(address);
            if (i % 20 == 0) { //hibernate.jdbc.batch_size
                em.flush(); //<8>
                em.clear(); //<9>
            }

        }
        Assertions.assertThat(addressRepo.count()).isEqualTo(100);
    }
// end::main[]
}