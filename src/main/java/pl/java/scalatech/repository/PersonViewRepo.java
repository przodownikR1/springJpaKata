package pl.java.scalatech.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import pl.java.scalatech.domain.PersonView;

public interface PersonViewRepo extends JpaRepository<PersonView, Long>{

}
