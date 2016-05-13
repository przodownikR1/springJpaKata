package pl.java.scalatech.domain.inheritence.per_class;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Data
@AllArgsConstructor
@NoArgsConstructor
public abstract class Product {
private static final long serialVersionUID = -802306391915956578L;
@Id @GeneratedValue(strategy=GenerationType.TABLE)
private Long id;

private String code;
private String name;
public Product(String code, String name) {
    super();
    this.code = code;
    this.name = name;
}


}