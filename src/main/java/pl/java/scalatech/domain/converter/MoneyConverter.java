package pl.java.scalatech.domain.converter;

import static javax.money.format.MonetaryFormats.getAmountFormat;

import java.util.Locale;

import javax.money.format.MonetaryAmountFormat;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import org.javamoney.moneta.Money;

@Converter
public class MoneyConverter implements AttributeConverter<Money, String> {

    private static final MonetaryAmountFormat FORMAT = getAmountFormat(Locale.ROOT);

    @Override
    public String convertToDatabaseColumn(final Money attribute) {
        return FORMAT.format(attribute);
    }

    @Override
    public Money convertToEntityAttribute(String dbData) {
        return Money.parse(dbData, FORMAT);
    }

}