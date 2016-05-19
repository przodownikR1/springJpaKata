package pl.java.scalatech.domain.oneToMany;

import java.util.ArrayList;
import java.util.Collection;

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

    private static final long serialVersionUID = -5143971188058760378L;

    private String login;

    @OneToMany(mappedBy = "user")
    private Collection<Address> address = new ArrayList<>();

}