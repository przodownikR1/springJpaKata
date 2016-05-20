package pl.java.scalatech.repository.person;

import org.springframework.data.jpa.repository.JpaRepository;

import pl.java.scalatech.domain.person.Person;

public interface PersonRepo extends JpaRepository<Person, Long>{

}
