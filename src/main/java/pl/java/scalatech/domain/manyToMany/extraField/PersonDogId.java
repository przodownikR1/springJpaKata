package pl.java.scalatech.domain.manyToMany.extraField;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class PersonDogId implements Serializable {

    private static final long serialVersionUID = -1262162988049789151L;
    private int person;
    private int dog;
}