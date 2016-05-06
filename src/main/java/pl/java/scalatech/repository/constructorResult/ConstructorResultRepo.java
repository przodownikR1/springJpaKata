package pl.java.scalatech.repository.constructorResult;

import org.springframework.data.jpa.repository.JpaRepository;

import pl.java.scalatech.domain.constructorResult.Todo;

public interface ConstructorResultRepo extends JpaRepository<Todo, Long>{

}
