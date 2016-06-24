package pl.java.scalatech.domain.inheritence.per_class;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

// tag::main[]
@Entity
@Table(name = "stock")
@NoArgsConstructor
@Data
public class StockProduct extends Product implements Serializable { // <1>
    private static final long serialVersionUID = 1620238240796817290L;
    private String currency;

    public StockProduct(String code, String name, String currency) {
        super(code, name);
        this.currency = currency;
    }
}
// end::main[]