package pl.java.scalatech.repository.lazy;

import org.springframework.data.jpa.repository.JpaRepository;

import pl.java.scalatech.domain.lazy.Item;

public interface ItemRepo extends JpaRepository<Item, Long>{

}
