package pl.java.scalatech.domain.jpql;

import javax.persistence.Entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import pl.java.scalatech.domain.AbstractEntity;
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString(callSuper=true)
@Builder

public class Department extends AbstractEntity {

    private static final long serialVersionUID = 3220770055008180686L;
    private String name;

}
