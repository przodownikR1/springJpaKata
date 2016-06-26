package pl.java.scalatech.domain.mapIds;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="MAPS_ID_Customer")
public class Customer {
@Id UserId userId;
  @MapsId("userId")
  @JoinColumns({
    @JoinColumn(name="userfirstname1", referencedColumnName="firstName"),
    @JoinColumn(name="userlastname1", referencedColumnName="lastName")
  })
  @OneToOne User user;
}

