package pl.java.scalatech.dbunit.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import pl.java.scalatech.dbunit.entity.Account;

public interface AccountRepo extends JpaRepository<Account, Long>{

}
