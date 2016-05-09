package pl.java.scalatech.domain.cartesian;

import javax.persistence.Column;
import javax.persistence.Entity;

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
public class Address  extends AbstractEntity{

    private static final long serialVersionUID = -773716531785360542L;
    @Column(name = "CITY")
    private String city = null;


}
