package pl.java.scalatech.repository;

import java.util.stream.Stream;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.base.Optional;

import pl.java.scalatech.domain.Person;
@Transactional(readOnly = true)
public interface PersonRepository extends JpaRepository<Person, Long> {

    Optional<Person> findByEmail(String email);

    @Query("select u from #{#entityName} u")
    Stream<Person>  all();

    @Query("select p from Person p")
    Stream<Person> streamAllPerson();

    Stream<Person> findById(long id);
}
