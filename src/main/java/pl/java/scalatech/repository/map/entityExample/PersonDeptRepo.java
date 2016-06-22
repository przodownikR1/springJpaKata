package pl.java.scalatech.repository.map.entityExample;

import org.springframework.data.jpa.repository.JpaRepository;

import pl.java.scalatech.domain.mapkey.entityExample.Person;

public interface PersonDeptRepo  extends JpaRepository<Person, Long>{

}
