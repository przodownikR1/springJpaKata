package pl.java.scalatech.repository.inheritence.per_table;

import org.springframework.data.jpa.repository.JpaRepository;

import pl.java.scalatech.domain.inheritence.per_class.DetailProduct;

public interface DetailProductRepo extends JpaRepository<DetailProduct, Long>{

}
