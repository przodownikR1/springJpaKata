package pl.java.scalatech.domain.inheritence.joined;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.ToString;

// tag::main[]
@Entity(name = "AccountJoin")
@Inheritance(strategy = InheritanceType.JOINED) // <1>
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class AccountJoin {

    @Id
    private Long id;

    private String owner;

    private BigDecimal balance;

    private BigDecimal interestRate;
}
// end::main[]