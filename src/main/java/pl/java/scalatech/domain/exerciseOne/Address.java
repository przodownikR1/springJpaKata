package pl.java.scalatech.domain.exerciseOne;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.java.scalatech.domain.AbstractEntity;

@Entity

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name="ADDRESS_EXERCISE")
public class Address extends AbstractEntity{

    private static final long serialVersionUID = 5748538040151447433L;
    @Column(name = "STREET")
    private String street;
    @Column(name = "CITY")
    private String city;

}