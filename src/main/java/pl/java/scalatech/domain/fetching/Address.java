package pl.java.scalatech.domain.fetching;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import pl.java.scalatech.domain.AbstractEntity;

@Entity
@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
@ToString(exclude="person")
public class Address  extends AbstractEntity{
    @Column(name = "CITY")
    private String city = null;

    private boolean disable;

    @ManyToOne
    @JoinColumn(name = "PERSON_ID")
    private Person person = null;


    @Column(name = "PERSON_ID", insertable = false, updatable = false)
    private Long personId = null;

}
