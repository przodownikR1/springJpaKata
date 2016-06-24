package pl.java.scalatech.domain.inheritence.single;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import lombok.Data;
import lombok.NoArgsConstructor;

// tag::main[]
@Entity
@DiscriminatorValue("groovy") //<1>
@Data
@NoArgsConstructor
public class GroovyTask extends Task {

    private String writeNewRule;

    public GroovyTask(String name, int hoursCost, HPerson person, String writeNewRule) {
        super(name, hoursCost, person);
        this.writeNewRule = writeNewRule;
    }

}
// end::main[]