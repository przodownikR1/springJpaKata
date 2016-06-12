package pl.java.scalatech.domain.examplePerformance1;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;

import lombok.Data;
import pl.java.scalatech.domain.AbstractEntity;
@Entity
@Data
@AttributeOverride(column=@Column(name = "jobId", nullable = false), name = "id")
public class Job extends AbstractEntity{

    private String name;

}
