package pl.java.scalatech.domain.oneToOne.bi;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
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
@Table(name = "USERS")
@ToString(exclude="address")
public class User extends AbstractEntity {

    private String login;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    protected Address address;


    // ...


    public void addAddress(Address address){
        address.setUser(this);
        setAddress(address);

    }

}