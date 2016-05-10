package pl.java.scalatech.domain.fetching;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.NamedEntityGraph;
import javax.persistence.NamedEntityGraphs;
import javax.persistence.OneToMany;

import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.FetchProfile;
import org.hibernate.annotations.FetchProfile.FetchOverride;
import org.hibernate.annotations.FetchProfiles;

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
@FetchProfiles({ @FetchProfile(name = "fetchJoinProfile", fetchOverrides = { @FetchOverride(
        entity = Person.class,
        association = "addresses",
        mode = FetchMode.JOIN) }) })

@NamedEntityGraphs({
@NamedEntityGraph
})
//@ToString(exclude="addresses")
public class Person extends AbstractEntity{

    private static final long serialVersionUID = -8324897794910579206L;
    private String firstName = null;
    private String lastName = null;

    @OneToMany(mappedBy = "person", cascade = CascadeType.ALL,fetch=FetchType.LAZY)
    @Column(name = "id")
   // @Fetch(org.hibernate.annotations.FetchMode.SUBSELECT)
    //@BatchSize(size = 2)

    private List<Address> addresses;
}
