package pl.java.scalatech.repository.map.collection;

import org.springframework.data.jpa.repository.JpaRepository;

import pl.java.scalatech.domain.mapkey.basic_collection.BookStore;

public interface BookStoreRepo extends JpaRepository<BookStore, Long>{

}
