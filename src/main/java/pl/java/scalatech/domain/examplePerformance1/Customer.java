package pl.java.scalatech.domain.examplePerformance1;

import java.util.List;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import lombok.Data;
import lombok.ToString;
import pl.java.scalatech.domain.AbstractEntity;
@Entity
@Data
@AttributeOverride(column=@Column(name = "customerId", nullable = false), name = "id")
@ToString(exclude={"jobs","location"})
public class Customer extends AbstractEntity{


    private String name;

    @OneToMany
    @JoinColumn(name="customerId")
    private List<Job> jobs;

    @OneToOne//(fetch=FetchType.LAZY)
    @JoinColumn(name="locationId")
    private Location location;
}
