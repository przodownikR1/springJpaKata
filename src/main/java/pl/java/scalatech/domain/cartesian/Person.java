package pl.java.scalatech.domain.cartesian;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import org.hibernate.annotations.BatchSize;

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
public class Person extends AbstractEntity{

    private static final long serialVersionUID = -4106601879598237198L;
    private String firstName = null;
    private String lastName = null;

    @OneToMany(cascade = CascadeType.ALL,fetch=FetchType.EAGER) //lazy
  //  @Fetch(FetchMode.SELECT)
    @BatchSize(size=7)
    @JoinColumn(name="PERSON_ID")
    private List<Address> addresses;


    @OneToMany(cascade = CascadeType.ALL,fetch=FetchType.EAGER)
  //  @Fetch(FetchMode.SELECT)
    @JoinColumn(name="PERSON_ID")
    private List<Phone> phones;

}
