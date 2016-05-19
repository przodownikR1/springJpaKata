package pl.java.scalatech.repository.mapping;

import org.springframework.data.jpa.repository.JpaRepository;

import pl.java.scalatech.domain.mapping.map.Account;

public interface MapRepository extends JpaRepository<Account,Long>{

}
