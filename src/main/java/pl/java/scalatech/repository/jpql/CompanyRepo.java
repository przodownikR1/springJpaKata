package pl.java.scalatech.repository.jpql;

import org.springframework.data.jpa.repository.JpaRepository;

import pl.java.scalatech.domain.jpql.Company;

public interface CompanyRepo extends JpaRepository<Company, Long>{

}
