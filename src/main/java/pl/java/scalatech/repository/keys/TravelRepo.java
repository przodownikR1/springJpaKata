package pl.java.scalatech.repository.keys;

import org.springframework.data.jpa.repository.JpaRepository;

import pl.java.scalatech.domain.keys.Travel;

public interface TravelRepo extends JpaRepository<Travel, Long>{

}
