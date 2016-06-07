package pl.java.scalatech.domain.oneToOne.joinTable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToOne;
import javax.persistence.Table;

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
@Table(name="User_o2o")
public class User extends AbstractEntity{

    private static final long serialVersionUID = -3106679565182223611L;

    //TODO ??? ALL
    @OneToOne(fetch = FetchType.LAZY,cascade={CascadeType.ALL})
    @JoinTable(name = "USER_ADDRESS", joinColumns = @JoinColumn(name = "USER_ID") , inverseJoinColumns = @JoinColumn(name = "ADDRESS_ID", nullable = false, unique = true) )
    private Address address;

    private String login;



}