package pl.java.scalatech.domain.inheritence.mapped;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity(name = "DebitAccount")
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name="MDebitAccount")
public class DebitAccount extends Account {

    private static final long serialVersionUID = -8161742730954756627L;
    private BigDecimal overdraftFee;
    public DebitAccount(String owner, BigDecimal balance, BigDecimal interestRate) {
        super(owner, balance, interestRate);
        overdraftFee = overdraftFee;
    }


}
