package pl.java.scalatech.domain.manyToMany.extraField;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
@Builder
@IdClass(PersonDogId.class)
public class PersonDog {
    @Id
    @ManyToOne
    @JoinColumn(name = "person_id")
    private Person person;
    @Id
    @ManyToOne
    @JoinColumn(name = "dog_id")
    private Dog dog;
    @Temporal(TemporalType.DATE)
    private Date adoptionDate;

}