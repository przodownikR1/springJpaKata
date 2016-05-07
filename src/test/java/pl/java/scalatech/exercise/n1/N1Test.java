package pl.java.scalatech.exercise.n1;

import java.util.List;

import javax.persistence.EntityManager;

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
import pl.java.scalatech.domain.example.n1.JobCandidate;
import pl.java.scalatech.repository.n1.CandidateRepo;
import pl.java.scalatech.repository.n1.SkillRepo;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {  PropertiesLoader.class,JpaJN1Config.class })
@ActiveProfiles(value = "n1")
@Transactional
@SqlDataN1
@Slf4j
public class N1Test {

    @Autowired
    private EntityManager em;
      
    @Autowired
    private SkillRepo skillRepo;
    
    @Autowired
    private CandidateRepo candidateRepo;
    
    
    @Test
    public void shouldBoostrap(){
 
    }

 
    @Test    
    public void shouldRetrieveSkills(){
        skillRepo.findAll().forEach(s->log.info("{}",s));
    }
    
    @Test
    public void shouldRetrieveSkillsUseFetchJoin(){
        skillRepo.findAllFetchJoin().forEach(s->log.info(" fetch join skill : {}",s));
    }
    
    @Test
    public void shouldRetrieveWhatIWant(){
        List<Object[]> result = em.createQuery("SELECT s.name, c.fullName FROM Skill s JOIN s.candidate c where c.fullName = :name").setParameter("name", "przodownik").getResultList();
        for(Object[] o : result){
            log.info("skill  name : {} , candidateName : {}",o[0],o[1]);
        }
        Assertions.assertThat(result).hasSize(4);
    }
    
    
  
}
