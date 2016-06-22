package pl.java.scalatech.repository.map.entityExample;

import org.springframework.data.jpa.repository.JpaRepository;

import pl.java.scalatech.domain.mapkey.entityExample.Company;

public interface CompanyRepo extends JpaRepository<Company, Long>{

}
