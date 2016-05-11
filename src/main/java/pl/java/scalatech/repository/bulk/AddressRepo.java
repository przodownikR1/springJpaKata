package pl.java.scalatech.repository.bulk;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import pl.java.scalatech.domain.bulk.Address;

public interface AddressRepo extends JpaRepository<Address, Long>{


    List<Address> findByDisableIsNull();

    List<Address> findByDisableTrue();
 }
