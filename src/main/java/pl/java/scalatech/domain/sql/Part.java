package pl.java.scalatech.domain.sql;

import javax.persistence.Entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.java.scalatech.domain.AbstractEntity;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Part extends AbstractEntity{

    private static final long serialVersionUID = -9045679133539211381L;
private String name;

private Boolean active;
}