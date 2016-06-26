package pl.java.scalatech.repository.manyToAny;

import org.springframework.data.jpa.repository.JpaRepository;

import pl.java.scalatech.domain.manyToAny.PropertyMap;

public interface PropertyMapRepo extends JpaRepository<PropertyMap, Long>{

}
