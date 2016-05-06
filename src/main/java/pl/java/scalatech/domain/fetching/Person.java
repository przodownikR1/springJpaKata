package pl.java.scalatech.domain.fetching;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Fetch;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.java.scalatech.domain.AbstractEntity;
@Entity
@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
//@ToString(exclude="addresses")
public class Person extends AbstractEntity{
    private String firstName = null;
    private String lastName = null;

    @OneToMany(mappedBy = "person", cascade = CascadeType.ALL)
    @Column(name = "id")
    @Fetch(org.hibernate.annotations.FetchMode.SUBSELECT)
    //@BatchSize(size = 6)

    private List<Address> addresses = new ArrayList<>();
}
