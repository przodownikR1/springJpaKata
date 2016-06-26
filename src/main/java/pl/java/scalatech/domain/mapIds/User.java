package pl.java.scalatech.domain.mapIds;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity 
@Table(name="MAPS_ID_USER")
class User {
  @EmbeddedId UserId id;
  Integer age;
}