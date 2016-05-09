package pl.java.scalatech.repository.cartesian;

import org.springframework.data.jpa.repository.JpaRepository;

import pl.java.scalatech.domain.cartesian.Phone;

public interface PhoneRepo extends JpaRepository<Phone, Long>{

}
