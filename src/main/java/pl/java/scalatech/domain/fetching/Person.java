package pl.java.scalatech.domain.fetching;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityResult;
import javax.persistence.FetchType;
import javax.persistence.FieldResult;
import javax.persistence.NamedEntityGraph;
import javax.persistence.NamedEntityGraphs;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.OneToMany;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.SqlResultSetMappings;
import javax.persistence.Table;

import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.Fetch;
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
//tag::main[]
@NoArgsConstructor
@FetchProfiles({ @FetchProfile(name = "fetchJoinProfile", fetchOverrides = { @FetchOverride(entity = Person.class, association = "addresses", mode = FetchMode.JOIN) }) })//<1>

@NamedEntityGraphs({ @NamedEntityGraph })//<2>

@NamedNativeQueries({
@NamedNativeQuery(name = "findPersonByfistName", query = "select * from Fetch_Person where firstname = :firstName", resultClass = Person.class),//<3>
@NamedNativeQuery(name = "queryPerson", query = "Select id, firstName, lastName,email,version from Fetch_Person p where p.id = :id ", resultSetMapping = "personResult")//<4>
})

@SqlResultSetMappings({ //<5>
    @SqlResultSetMapping(name = "personResult",
            entities = @EntityResult(entityClass = Person.class, //<6>
            fields = {
                    @FieldResult(name = "id", column = "id"),
                    @FieldResult(name = "firstName", column = "firstName"),
                    @FieldResult(name = "lastName", column = "lastName"),
                    @FieldResult(name = "email", column = "email"),
                    @FieldResult(name = "version", column = "version")

        })
    ) })
// @ToString(exclude="addresses")
@Table(name="FETCH_PERSON")
public class Person extends AbstractEntity {

    private static final long serialVersionUID = -8324897794910579206L;
    @Column(name="firstName")
    private String firstName = null;
    @Column(name="lastName")
    private String lastName = null;

    private String email;

    @OneToMany(mappedBy = "person", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @Column(name = "id")
    @Fetch(org.hibernate.annotations.FetchMode.SUBSELECT) //<7>
    @BatchSize(size = 10) //<8>
    private List<Address> addresses;
}
// end::main[]