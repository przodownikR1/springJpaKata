package pl.java.scalatech.domain.inheritence.joined;

import java.math.BigDecimal;

import javax.persistence.Entity;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity(name = "DebitAccount")
@AllArgsConstructor
@NoArgsConstructor
@ToString
// tag::main[]
public class DebitAccountJoin extends AccountJoin {

    private BigDecimal overdraftFee;

    public DebitAccountJoin(Long id, String owner, BigDecimal balance, BigDecimal interestRate, BigDecimal overdraftFee) {
        super(id, owner, balance, interestRate);
        this.overdraftFee = overdraftFee;
    }

}
// end::main[]
