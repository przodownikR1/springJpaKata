package pl.java.scalatech.constructorResult;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lombok.extern.slf4j.Slf4j;
import pl.java.scalatech.config.TestJpaConfig;
import pl.java.scalatech.domain.constructorResult.Todo;
import pl.java.scalatech.pojo.TodoDTO;
import pl.java.scalatech.repository.constructorResult.ConstructorResultRepo;

@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={TestJpaConfig.class})
public class ConstructorResultTest {
    @Autowired
    private ConstructorResultRepo repo;
    @Autowired
    private EntityManager em;

    @Test
    public void shouldSaveAndThanFind(){
        assertThat(repo).isNotNull();
        repo.save(new Todo("all", "java"));
        repo.save(new Todo("all", "jsp"));
        repo.save(new Todo("my", "maven"));
        repo.save(new Todo("you", "gradle"));
        Query query = em.createNamedQuery("findWithTodoResultSetMapper",TodoDTO.class);
        query.setParameter(1, "ja%");
        List<TodoDTO> result =  query.getResultList();
        log.info("{}",result);
        Assertions.assertThat(result).hasSize(1);
    }

}
