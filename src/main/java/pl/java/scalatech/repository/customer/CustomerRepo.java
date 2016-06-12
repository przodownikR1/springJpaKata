package pl.java.scalatech.repository.customer;

import org.springframework.data.jpa.repository.JpaRepository;

import pl.java.scalatech.domain.examplePerformance1.Customer;

public interface CustomerRepo extends JpaRepository<Customer, Long>{

}
