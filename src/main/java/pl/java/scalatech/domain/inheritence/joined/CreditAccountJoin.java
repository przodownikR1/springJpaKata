package pl.java.scalatech.domain.inheritence.joined;

import java.math.BigDecimal;

import javax.persistence.Entity;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity(name = "CreditAccount")
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CreditAccountJoin extends AccountJoin {

    private BigDecimal creditLimit;

    public CreditAccountJoin(Long id, String owner, BigDecimal balance, BigDecimal interestRate,BigDecimal creditLimit) {
        super(id, owner, balance, interestRate);
        this.creditLimit = creditLimit;

    }

}