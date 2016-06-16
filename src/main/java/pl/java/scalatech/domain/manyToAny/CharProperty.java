package pl.java.scalatech.domain.manyToAny;

import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.java.scalatech.domain.AbstractEntity;

@Entity
@Table( name = "char_property" )
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CharProperty extends AbstractEntity implements Property {

	private String name;

	private Character value;

		@Override
    public String asString() {
		return Character.toString( value );
	}

        @Override
        public String getName() {
            return name;
        }


}