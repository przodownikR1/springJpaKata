package pl.java.scalatech.domain.manyToAny;

import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.java.scalatech.domain.AbstractEntity;

@Entity
@Table(name = "long_property")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class LongProperty extends AbstractEntity implements Property {

    private static final long serialVersionUID = -1275713912865305945L;

    private String name;

    private Long value;


    @Override
    public String asString() {
        return Long.toString(value);
    }

    @Override
    public String getName() {
        return name;
    }



}