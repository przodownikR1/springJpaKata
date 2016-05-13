package pl.java.scalatech.repository.inheritence.joined;

import org.springframework.data.jpa.repository.JpaRepository;

import pl.java.scalatech.domain.inheritence.joined.DebitAccountJoin;

public interface DebitJoinRepo extends JpaRepository<DebitAccountJoin, Long>{

}
