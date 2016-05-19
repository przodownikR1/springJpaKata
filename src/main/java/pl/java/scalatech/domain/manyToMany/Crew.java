package pl.java.scalatech.domain.manyToMany;

import javax.persistence.Entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.java.scalatech.domain.AbstractEntity;
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Crew extends AbstractEntity{


    /**
     *
     */
    private static final long serialVersionUID = -1130489364466402054L;
    private int count;

}
