package pl.java.scalatech.repository.inheritence.mapped;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;

import pl.java.scalatech.domain.inheritence.mapped.CreditAccount;

public interface CreditAccoutRepo extends JpaRepository<CreditAccount, Long>{

}
