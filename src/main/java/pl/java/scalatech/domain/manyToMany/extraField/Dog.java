package pl.java.scalatech.domain.manyToMany.extraField;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import pl.java.scalatech.domain.AbstractEntity;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
@Builder
public class Dog extends AbstractEntity {
    private String name;
    @OneToMany(mappedBy = "dog")
    private List<PersonDog> persons;

}