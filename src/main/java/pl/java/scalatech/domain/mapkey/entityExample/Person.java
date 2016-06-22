package pl.java.scalatech.domain.mapkey.entityExample;

import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.java.scalatech.domain.AbstractEntity;
@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name="MAP_PERSON")
public class Person extends AbstractEntity{

    private static final long serialVersionUID = -3568079577829876072L;
    private String name;
    private int age;
  
}
