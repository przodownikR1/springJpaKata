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
public class Address extends AbstractEntity{

    private static final long serialVersionUID = -1430837420722733736L;
    private String street;

    @OneToMany(mappedBy = "address")
    private Set<UsersAddress> usersAddress = new HashSet<>();

}
