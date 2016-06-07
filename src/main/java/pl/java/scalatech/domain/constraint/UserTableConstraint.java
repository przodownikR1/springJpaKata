package pl.java.scalatech.domain.constraint;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.java.scalatech.domain.AbstractEntity;

@Entity
@Table(
        name = "USERS_CONSTRAINTS",
        uniqueConstraints =
            @UniqueConstraint(
                name = "UNQ_USERNAME_EMAIL",
                columnNames = { "USERNAME", "EMAIL" }
            )
        ,
        indexes = {
            @Index(
                name = "IDX_USERNAME",
                columnList = "USERNAME"
            ),
            @Index(
                name = "IDX_USERNAME_EMAIL",
                columnList = "USERNAME, EMAIL"
            )
        }
    )
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class UserTableConstraint extends AbstractEntity {

    private static final long serialVersionUID = -3345276438359496780L;

    @Column(nullable = false, unique = true, columnDefinition = "EMAIL_ADDRESS(255)")
    protected String email;

    @Column(columnDefinition = "varchar(15) not null unique" + " check (not substring(lower(USERNAME), 0, 5) = 'admin')")
    protected String username;

}
