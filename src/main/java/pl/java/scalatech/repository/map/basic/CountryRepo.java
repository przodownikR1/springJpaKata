package pl.java.scalatech.repository.map.basic;

import org.springframework.data.jpa.repository.JpaRepository;

import pl.java.scalatech.domain.mapkey.basic_mapKey.Country;

public interface CountryRepo extends JpaRepository<Country, Long>{

}
