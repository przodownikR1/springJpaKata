package pl.java.scalatech.domain.converter;

import javax.money.MonetaryAmount;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true) // Default for MonetaryAmount properties
public class MonetaryAmountConverter
    implements AttributeConverter<MonetaryAmount, String> {

    @Override
    public String convertToDatabaseColumn(MonetaryAmount monetaryAmount) {
        return monetaryAmount.toString();
    }
    //TODO
    @Override
    public MonetaryAmount convertToEntityAttribute(String s) {
       return null;
    }
}