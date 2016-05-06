package pl.java.scalatech.domain.jpql;

import java.math.BigDecimal;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.NaturalId;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import pl.java.scalatech.domain.AbstractEntity;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class Employee extends AbstractEntity{

    private static final long serialVersionUID = -6662265149193507913L;
    private String firstName;
    private String lastName;
    private BigDecimal salary;
    private int age;
    @ManyToOne(cascade=CascadeType.ALL)
    private Company company;
    @NaturalId
    private String pesel;
}
