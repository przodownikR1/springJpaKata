package pl.java.scalatech.domain.oneToOne.joinTable;

import javax.persistence.Entity;

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
public class Address extends AbstractEntity{

    private static final long serialVersionUID = -4668553614719853285L;
    private String street;
}
