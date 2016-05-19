package pl.java.scalatech.domain.generator;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import lombok.Getter;
import lombok.ToString;

@MappedSuperclass
@ToString
public abstract class TableGenerator {
    // @formatter:off
    @GeneratedValue( generator="TableIdGen")
    @GenericGenerator(strategy="org.hibernate.id.enhanced.TableGenerator",name="TableIdGen",
    parameters = {
    @Parameter(name = "table_name", value = "enhanced_hibernate_sequences"),
    @Parameter(name = "segment_value", value = "id"),
    @Parameter(name = "optimizer", value = "pooled"),
    @Parameter(name = "initial_value", value = "1000"),
    @Parameter(name = "increment_size", value = "10")
    })
    // @formatter:on
    @Id
    @Getter
    protected Long id;
}
