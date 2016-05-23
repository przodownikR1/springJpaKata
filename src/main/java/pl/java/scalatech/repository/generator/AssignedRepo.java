package pl.java.scalatech.repository.generator;

import org.springframework.data.jpa.repository.JpaRepository;

import pl.java.scalatech.domain.generator.AssignedEntity;

public interface AssignedRepo extends JpaRepository<AssignedEntity, Long>{


}
