package pl.java.scalatech.domain.oneToMany.table;

import javax.persistence.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.java.scalatech.domain.AbstractEntity;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Part extends AbstractEntity{

    private static final long serialVersionUID = -9045679133539211381L;
private String name;
}