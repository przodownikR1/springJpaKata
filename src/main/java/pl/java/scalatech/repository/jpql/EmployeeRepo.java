package pl.java.scalatech.repository.jpql;

import org.springframework.data.jpa.repository.JpaRepository;

import pl.java.scalatech.domain.jpql.Employee;

public interface EmployeeRepo extends JpaRepository<Employee, Long>{

}
