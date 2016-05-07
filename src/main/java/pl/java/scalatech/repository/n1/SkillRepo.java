package pl.java.scalatech.repository.n1;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import pl.java.scalatech.domain.example.n1.Skill;

public interface SkillRepo extends JpaRepository<Skill, Long>{
    
    @Query(value = "SELECT s FROM Skill s LEFT JOIN FETCH s.candidate c")
    List<Skill> findAllFetchJoin();
}
