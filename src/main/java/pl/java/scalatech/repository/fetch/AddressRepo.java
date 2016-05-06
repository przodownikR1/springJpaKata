package pl.java.scalatech.repository.fetch;

import org.springframework.data.jpa.repository.JpaRepository;

import pl.java.scalatech.domain.fetching.Address;

public interface AddressRepo extends JpaRepository<Address, Long>{

}
