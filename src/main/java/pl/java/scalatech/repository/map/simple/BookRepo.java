package pl.java.scalatech.repository.map.simple;

import org.springframework.data.jpa.repository.JpaRepository;

import pl.java.scalatech.domain.mapkey.simple.Book;

public interface BookRepo extends JpaRepository<Book, Long>{

}
