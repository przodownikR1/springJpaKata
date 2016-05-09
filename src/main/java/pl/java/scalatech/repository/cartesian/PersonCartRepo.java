package pl.java.scalatech.repository.cartesian;

import org.springframework.data.jpa.repository.JpaRepository;

import pl.java.scalatech.domain.cartesian.Person;

public interface PersonCartRepo extends JpaRepository<Person, Long>{

}
