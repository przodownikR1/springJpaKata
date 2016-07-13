package pl.java.scalatech.dbunit.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import pl.java.scalatech.dbunit.entity.Client;

public interface ClientRepo extends JpaRepository<Client, Long>{

}
