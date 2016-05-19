package pl.java.scalatech.domain.manyToMany.extraField;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import pl.java.scalatech.domain.AbstractEntity;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class Person extends AbstractEntity {

    private static final long serialVersionUID = -6358182466024509810L;
    private String name;
    @OneToMany(mappedBy = "person")
    private List<PersonDog> dogs;

}