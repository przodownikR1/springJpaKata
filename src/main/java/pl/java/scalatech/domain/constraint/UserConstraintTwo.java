package pl.java.scalatech.domain.constraint;

import javax.persistence.Column;
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
public class UserConstraintTwo extends AbstractEntity{
    private static final long serialVersionUID = -4825311353412697430L;
    @Column(columnDefinition =
            "varchar(15) not null unique" +
            " check (not substring(lower(USERNAME), 0, 5) = 'admin')"
            )
            protected String username;


}