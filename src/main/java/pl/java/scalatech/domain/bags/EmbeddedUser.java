package pl.java.scalatech.domain.bags;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.AttributeOverride;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.java.scalatech.domain.AbstractEntity;
import pl.java.scalatech.domain.common.Address;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class EmbeddedUser extends AbstractEntity{

    private static final long serialVersionUID = 5277647989661060933L;
    @ElementCollection
    @CollectionTable(name = "ADDRESS_NOW")
    @AttributeOverride(
            name = "street",
            column = @Column(name = "U_STREET", nullable = false)
            )
    private Set<Address> images = new HashSet<>();


}
