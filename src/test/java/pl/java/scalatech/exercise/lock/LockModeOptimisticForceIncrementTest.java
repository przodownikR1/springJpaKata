package pl.java.scalatech.exercise.lock;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.slf4j.Slf4j;
import pl.java.scalatech.config.JpaLoggerConfig;
import pl.java.scalatech.config.PropertiesLoader;
import pl.java.scalatech.exercise.n1.SqlDataN1;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {  PropertiesLoader.class,LockConfig.class,JpaLoggerConfig.class })
@ActiveProfiles(value = {"lock","logger"})
@Transactional
@SqlDataN1
@Slf4j
public class LockModeOptimisticForceIncrementTest extends AbstractTransaction {


    @Before
    public void init() {

       /* doInTransaction(session -> {
            Repository repository = new Repository("Hibernate-Master-Class");
            session.persist(repository);
            session.flush();
        });*/

    }

    @Test
    public void testOptimisticForceIncrementLocking() throws InterruptedException {
        log.info("Test Single OPTIMISTIC_FORCE_INCREMENT Lock Mode ");
       /* doInTransaction(session -> {
            Repository repository = session.get(Repository.class, 1L);
            session.buildLockRequest(new LockOptions(LockMode.OPTIMISTIC_FORCE_INCREMENT)).lock(repository);
            Commit commit = new Commit(repository);
            commit.getChanges().add(new Change("README.txt", "0a1,5..."));
            commit.getChanges().add(new Change("web.xml", "17c17..."));
            session.persist(commit);
        });*/
    }

    @Test
    public void testConcurrentOptimisticForceIncrementLocking() throws InterruptedException {
        log.info("Test Concurrent OPTIMISTIC_FORCE_INCREMENT Lock Mode ");
      /*  try {
            doInTransaction(session -> {
                Repository repository = session.get(Repository.class, 1L);
                session.buildLockRequest(new LockOptions(LockMode.OPTIMISTIC_FORCE_INCREMENT)).lock(repository);

                executeSync(() -> {
                    doInTransaction(_session -> {
                        Repository _repository = _session.get(Repository.class, 1L);
                        _session.buildLockRequest(new LockOptions(LockMode.OPTIMISTIC_FORCE_INCREMENT)).lock(_repository);
                        Commit _commit = new Commit(_repository);
                        _commit.getChanges().add(new Change("index.html", "0a1,2..."));
                        _session.persist(_commit);
                    });
                });

                Commit commit = new Commit(repository);
                commit.getChanges().add(new Change("README.txt", "0a1,5..."));
                commit.getChanges().add(new Change("web.xml", "17c17..."));
                session.persist(commit);
            });
            fail("Should have thrown StaleObjectStateException!");
        } catch (StaleObjectStateException expected) {
            log.info("Failure: ", expected);
        }*/
    }


}