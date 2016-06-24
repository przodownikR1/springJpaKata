package pl.java.scalatech.exercise.lazy;

import static com.google.common.collect.Maps.newHashMap;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.Map;

import javax.persistence.EntityGraph;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.assertj.core.api.Assertions;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.slf4j.Slf4j;
import pl.java.scalatech.config.JpaLoggerConfig;
import pl.java.scalatech.config.PropertiesLoader;
import pl.java.scalatech.domain.lazy.Item;
import pl.java.scalatech.repository.lazy.ItemRepo;
import pl.java.scalatech.repository.lazy.OfferRepo;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { PropertiesLoader.class, JpaLazyConfig.class,JpaLoggerConfig.class })
@ActiveProfiles(value = {"lazy","logger","dev"})
@Transactional
@Slf4j
// @SqlDataAccount
@Sql(scripts = "classpath:lazy.sql")
public class JpaLazyTest {

    @Autowired
    private ItemRepo itemRepo;

    @Autowired
    private OfferRepo offerRepo;

    @Autowired
    private EntityManager em;

    @Test
    public void shouldBootstrap() {
        Assertions.assertThat(offerRepo.count()).isEqualTo(11);
        Assertions.assertThat(itemRepo.count()).isEqualTo(3);

    }
//tag::main[]
    @Test
    public void shouldLazyProxy() {
        Item one = em.find(Item.class, 1l);
        assertThat(Persistence.getPersistenceUtil().isLoaded(one)).isTrue(); //<1>
        assertThat(Persistence.getPersistenceUtil().isLoaded(one.getOffers())).isFalse();//<2>
    }

    @Test
    public void shouldLazyInitializationSolution1() {
        Item one = em.find(Item.class, 1l);
        assertThat(Persistence.getPersistenceUtil().isLoaded(one)).isTrue();//<3>
        log.info("{}",one.getOffers().size());
        assertThat(Persistence.getPersistenceUtil().isLoaded(one.getOffers())).isTrue();//<4>
    }

    @Test
    public void shouldLazyInitializationSolution2() {
        Item one = em.createQuery("SELECT i FROM Item i join fetch i.offers WHERE i.id = :id", Item.class).setParameter("id", 1l).getSingleResult(); //<5>
        assertThat(Persistence.getPersistenceUtil().isLoaded(one)).isTrue(); //<6>
        assertThat(Persistence.getPersistenceUtil().isLoaded(one.getOffers())).isTrue(); //<7>

    }

    @Test
    public void shouldLazyInitializationSolution3() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Item> criteria = cb.createQuery(Item.class);
        Root<Item> i = criteria.from(Item.class);
        i.fetch("offers"); //<8>
        criteria.select(i).where(cb.equal(i.get("id"), 1l));
        Item one = em.createQuery(criteria).getSingleResult();
        assertThat(Persistence.getPersistenceUtil().isLoaded(one)).isTrue(); //<9>
        assertThat(Persistence.getPersistenceUtil().isLoaded(one.getOffers())).isTrue(); //<10>

    }

    @Test
    public void shouldLazyInitializationSolution4() {
        Map<String, Object> props = newHashMap();
         em.getEntityGraphs(Item.class).forEach(eg->log.info("{}",eg)); //<11>
         props.put("javax.persistence.loadgraph", em.getEntityGraph("offers")); //<12>
        Item one = em.find(Item.class, 1l,props);
        assertThat(Persistence.getPersistenceUtil().isLoaded(one)).isTrue(); 
        assertThat(Persistence.getPersistenceUtil().isLoaded(one.getOffers())).isTrue();
    }

    @Test
    public void shouldLazyInitializationSolution5() {
        Map<String, Object> hints = newHashMap();
        EntityGraph<Item> itemGraph = em.createEntityGraph(Item.class); //<13>
        itemGraph.addAttributeNodes("offers"); //<14>
        hints.put("javax.persistence.loadgraph", itemGraph); //<15>
        em.getEntityGraphs(Item.class).forEach(eg->log.info("{}",eg));
        Item one = em.find(Item.class, 1l,hints);
        assertThat(Persistence.getPersistenceUtil().isLoaded(one)).isTrue();
        assertThat(Persistence.getPersistenceUtil().isLoaded(one.getOffers())).isTrue();
    }
// end::main[]
    @Test
    @Ignore //TODO
    public void shouldDynamicInsertAndUpdate(){
        Item item = Item.builder().name("slawek").build();
            itemRepo.save(item);
            log.info("{}",itemRepo.findAll());
    }

}
