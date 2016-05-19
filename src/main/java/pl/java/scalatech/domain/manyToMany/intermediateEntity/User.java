package pl.java.scalatech.domain.manyToMany.intermediateEntity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

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
public class User extends AbstractEntity {

    private static final long serialVersionUID = -8336341194996632207L;
    private String login;

    @OneToMany(mappedBy = "user")
    private Set<UsersAddress> usersAddress = new HashSet<>();

}
