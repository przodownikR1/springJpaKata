package pl.java.scalatech.repository.inheritence.per_table;

import org.springframework.data.jpa.repository.JpaRepository;

import pl.java.scalatech.domain.inheritence.per_class.Product;

public interface ProductRepo extends JpaRepository<Product, Long> {

}
