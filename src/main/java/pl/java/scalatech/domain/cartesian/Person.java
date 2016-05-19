package pl.java.scalatech.domain.cartesian;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

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
@Table(name="CARTESIAN_PERSON")
public class Person extends AbstractEntity{

    private static final long serialVersionUID = -4106601879598237198L;
    private String firstName = null;
    private String lastName = null;

    @OneToMany(cascade = CascadeType.ALL,fetch=FetchType.EAGER)
    @Fetch(FetchMode.SUBSELECT)
    @JoinColumn(name="PERSON_ID")
    private Set<Address> addresses;


    @OneToMany(cascade = CascadeType.ALL,fetch=FetchType.EAGER)

    @JoinColumn(name="PERSON_ID")
    private Set<Phone> phones;

}
