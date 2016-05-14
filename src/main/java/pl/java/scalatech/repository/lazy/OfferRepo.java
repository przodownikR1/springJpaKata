package pl.java.scalatech.repository.lazy;

import org.springframework.data.jpa.repository.JpaRepository;

import pl.java.scalatech.domain.lazy.Offer;

public interface OfferRepo extends JpaRepository<Offer,Long>{

}
