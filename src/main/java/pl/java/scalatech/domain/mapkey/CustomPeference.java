package pl.java.scalatech.domain.mapkey;

import javax.persistence.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.java.scalatech.domain.AbstractEntity;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomPeference extends AbstractEntity{

    private String name;

}