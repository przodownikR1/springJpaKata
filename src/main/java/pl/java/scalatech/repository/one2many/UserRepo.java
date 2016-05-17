package pl.java.scalatech.repository.one2many;

import org.springframework.data.jpa.repository.JpaRepository;

import pl.java.scalatech.domain.example.one2many.User;

public interface UserRepo extends JpaRepository<User, Long>{

}
