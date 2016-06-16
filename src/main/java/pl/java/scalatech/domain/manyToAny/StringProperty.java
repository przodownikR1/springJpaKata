package pl.java.scalatech.domain.manyToAny;

import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.java.scalatech.domain.AbstractEntity;

@Entity
@Table(name="string_property")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class
StringProperty extends AbstractEntity implements Property {

    private static final long serialVersionUID = -4928360703691383693L;
    private String name;
	private String value;

	@Override
    public String getName() {
		return name;
	}

	@Override
    public String asString() {
		return value;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public void setName(String name) {
		this.name = name;
	}
}