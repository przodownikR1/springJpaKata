package pl.java.scalatech.repository.inheritence.mapped;

import org.springframework.data.jpa.repository.JpaRepository;

import pl.java.scalatech.domain.inheritence.mapped.DebitAccount;

public interface DebitAccountRepo extends JpaRepository<DebitAccount, Long>{

}
