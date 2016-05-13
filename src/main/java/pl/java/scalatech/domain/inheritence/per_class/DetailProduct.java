package pl.java.scalatech.domain.inheritence.per_class;

import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.javamoney.moneta.Money;

import lombok.Data;
import lombok.NoArgsConstructor;
import pl.java.scalatech.domain.converter.MoneyConverter;

@Entity
@Table(name="details")

@Data
@NoArgsConstructor
public class DetailProduct extends Product{

    @Convert(converter = MoneyConverter.class)
    private Money price;

    public DetailProduct(String code, String name, Money price) {
        super( code, name);
        this.price  = price;
    }


}
