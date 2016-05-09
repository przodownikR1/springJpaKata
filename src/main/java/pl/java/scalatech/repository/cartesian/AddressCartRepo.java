package pl.java.scalatech.repository.cartesian;

import org.springframework.data.jpa.repository.JpaRepository;

import pl.java.scalatech.domain.cartesian.Address;

public interface AddressCartRepo extends JpaRepository<Address, Long>{

}
