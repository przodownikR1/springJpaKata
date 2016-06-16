package pl.java.scalatech.domain.oneToMany.table;


import javax.persistence.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.java.scalatech.domain.AbstractEntity;
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProductCustomer extends AbstractEntity {

    private static final long serialVersionUID = -1529631784715682632L;
    private String name;
}
