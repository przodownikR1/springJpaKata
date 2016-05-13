package pl.java.scalatech.repository.inheritence.joined;

import org.springframework.data.jpa.repository.JpaRepository;

import pl.java.scalatech.domain.inheritence.joined.CreditAccountJoin;

public interface CreditJoinRepo extends JpaRepository<CreditAccountJoin, Long>{

}
