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
public class User extends AbstractEntity {

    private static final long serialVersionUID = 8907371194850316019L;
    @Column(nullable = false, unique = true, columnDefinition = "EMAIL_ADDRESS(255)")
    private String email;

}