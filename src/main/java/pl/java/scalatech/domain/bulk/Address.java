package pl.java.scalatech.domain.bulk;

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
@Builder
@NoArgsConstructor
@Table(name="Bulk_Address")
public class Address  extends AbstractEntity{

    private static final long serialVersionUID = -7478171078449579045L;

    @Column(name = "CITY")
    private String city = null;

    private Boolean disable;





}
