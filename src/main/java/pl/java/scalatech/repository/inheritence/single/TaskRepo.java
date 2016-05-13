package pl.java.scalatech.repository.inheritence.single;

import org.springframework.data.jpa.repository.JpaRepository;

import pl.java.scalatech.domain.inheritence.single.Task;

public interface TaskRepo extends JpaRepository<Task, Long>{

}
