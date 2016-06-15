package pl.java.scalatech.repository.keys;

import org.springframework.data.jpa.repository.JpaRepository;

import pl.java.scalatech.domain.keys.Trip;

public interface TripRepo extends JpaRepository<Trip, Long>{

}
