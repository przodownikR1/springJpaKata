package pl.java.scalatech.repository.fetch;

import org.springframework.data.jpa.repository.JpaRepository;

import pl.java.scalatech.domain.fetching.Person;

public interface PersonRepo extends JpaRepository<Person, Long>{

}
