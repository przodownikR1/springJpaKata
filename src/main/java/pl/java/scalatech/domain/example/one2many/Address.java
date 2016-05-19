package pl.java.scalatech.domain.example.one2many;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name="Address_O2M")
public class Address extends AbstractEntity{

    private static final long serialVersionUID = -8341359347392852978L;


    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private User user;

    private String street;
}
