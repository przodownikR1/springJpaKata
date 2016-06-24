package pl.java.scalatech.domain.inheritence.per_class;

import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.javamoney.moneta.Money;

import lombok.Data;
import lombok.NoArgsConstructor;
import pl.java.scalatech.domain.converter.MoneyConverter;

// tag::main[]
@Entity
@Table(name = "details")
@Data
@NoArgsConstructor
public class DetailProduct extends Product { // <1>

    @Convert(converter = MoneyConverter.class) //<2>
    private Money price;

    public DetailProduct(String code, String name, Money price) {
        super(code, name);
        this.price = price;
    }
}
// end::main[]