package pl.java.scalatech.domain.inheritence.mapped;

import java.math.BigDecimal;

import javax.persistence.MappedSuperclass;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import pl.java.scalatech.domain.AbstractEntity;

@MappedSuperclass
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public  class Account extends AbstractEntity{

    private static final long serialVersionUID = -1724603225534607888L;

    private String owner;

    private BigDecimal balance;

    private BigDecimal interestRate;
}