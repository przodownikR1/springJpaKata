package pl.java.scalatech.domain.selfReference;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.java.scalatech.domain.AbstractEntity;

@Entity
@Table(name="SELF_CATEGORIES")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Category extends AbstractEntity{


    private static final long serialVersionUID = -7477305898410276904L;
    private String name;
    @ManyToOne(cascade=CascadeType.ALL)
    private Category parent;


}