package pl.java.scalatech.repository.inheritence.single;

import org.springframework.data.jpa.repository.JpaRepository;

import pl.java.scalatech.domain.inheritence.single.JpaTask;

public interface JpaTaskRepo extends JpaRepository<JpaTask, Long>{

}
