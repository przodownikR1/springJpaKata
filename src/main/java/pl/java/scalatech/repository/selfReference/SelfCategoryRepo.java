package pl.java.scalatech.repository.selfReference;

import org.springframework.data.jpa.repository.JpaRepository;

import pl.java.scalatech.domain.selfReference.Category;

public interface SelfCategoryRepo extends JpaRepository<Category, Long>{

}
