package pl.java.scalatech.domain.oneToOne.bi;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import pl.java.scalatech.domain.AbstractEntity;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@ToString(exclude="user")
public class Address extends AbstractEntity {

    private static final long serialVersionUID = 3359220441890883540L;

    private String street;

    @OneToOne(optional = false)
    private User user;
}