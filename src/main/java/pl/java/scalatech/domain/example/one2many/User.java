package pl.java.scalatech.domain.example.one2many;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

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
@Builder
@ToString(exclude="address")
@Table(name="USER_O2M")
public class User extends AbstractEntity {

    private static final long serialVersionUID = -5143971188058760378L;

    private String login;

    @OneToMany(mappedBy = "user")
    private List<Address> address = new ArrayList<>();

    public void addAddress(Address address){
        address.setUser(this);
        getAddress().add(address);
    }

}