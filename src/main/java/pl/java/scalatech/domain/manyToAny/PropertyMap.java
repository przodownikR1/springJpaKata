package pl.java.scalatech.domain.manyToAny;

import static com.google.common.collect.Lists.newArrayList;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Table;

import org.hibernate.annotations.AnyMetaDef;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.ManyToAny;
import org.hibernate.annotations.MetaValue;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.java.scalatech.domain.AbstractEntity;

@Entity
@Table(name = "Property_Example")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class PropertyMap extends AbstractEntity {

    private static final long serialVersionUID = 6842457337123716680L;

    private String name;
    
    @ManyToAny(metaColumn = @Column(name = "property_type"))
    @AnyMetaDef(idType = "long", metaType = "string", metaValues = { 
            @MetaValue(value = "S", targetEntity = StringProperty.class),
            @MetaValue(value = "L", targetEntity = LongProperty.class) })
    @Cascade({ org.hibernate.annotations.CascadeType.ALL })
    @JoinTable(name = "obj_properties", joinColumns = @JoinColumn(name = "obj_id"), inverseJoinColumns = @JoinColumn(name = "property_id"))
    private List<Property> properties = newArrayList();

    public PropertyMap(String name) {
        this.name = name;
    }

}