package pl.java.scalatech.domain.example;

import javax.persistence.Convert;
import javax.persistence.Entity;

import org.javamoney.moneta.Money;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.ToString;
import pl.java.scalatech.domain.AbstractEntity;
import pl.java.scalatech.domain.converter.MoneyConverter;
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class SimpleProduct extends AbstractEntity{
    
    private String name;
    @Convert(converter = MoneyConverter.class)
    private Money price;
    

}
