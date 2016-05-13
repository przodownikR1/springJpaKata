package pl.java.scalatech.repository.inheritence.single;

import org.springframework.data.jpa.repository.JpaRepository;

import pl.java.scalatech.domain.inheritence.single.JavaTask;

public interface JavaTaskRepo extends JpaRepository<JavaTask, Long>{

}
