package pl.java.scalatech.repository.exerciseOne;

import org.springframework.data.jpa.repository.JpaRepository;

import pl.java.scalatech.domain.exerciseOne.Employee;

public interface EmployeeRepo extends JpaRepository<Employee, Long>{

}
