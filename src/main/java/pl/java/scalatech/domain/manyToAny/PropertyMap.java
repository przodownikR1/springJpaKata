package pl.java.scalatech.domain.manyToAny;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.MapKey;
import javax.persistence.Table;

import org.hibernate.annotations.AnyMetaDef;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.ManyToAny;
import org.hibernate.annotations.MetaValue;

import javafx.beans.property.IntegerProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.java.scalatech.domain.AbstractEntity;


//@Entity
@Table( name = "property_map" )
@AllArgsConstructor
@NoArgsConstructor
@Data
public class PropertyMap extends AbstractEntity{

    private static final long serialVersionUID = 6842457337123716680L;

	private String name;

	private Map<String, Property> properties = new HashMap<>();

	public PropertyMap(String name) {
		this.name = name;
	}

	@ManyToAny( metaColumn = @Column( name = "property_type" ) )
	@AnyMetaDef(
			idType = "integer", metaType = "string",
			metaValues = {
			@MetaValue( value = "S", targetEntity = StringProperty.class ),
			@MetaValue( value = "I", targetEntity = IntegerProperty.class ) } )
	@Cascade( org.hibernate.annotations.CascadeType.ALL )
	@JoinTable(
			name = "map_properties",
			joinColumns = @JoinColumn( name = "map_id" ),
			inverseJoinColumns = @JoinColumn( name = "property_id" ) )
	@MapKey
	public Map<String, Property> getProperties() {
		return properties;
	}

	public void setProperties(Map<String, Property> properties) {
		this.properties = properties;
	}


}