package pl.java.scalatech.domain.inheritence.single;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import lombok.Data;
import lombok.NoArgsConstructor;

// tag::main[]
@Entity
@DiscriminatorValue("cd") //<1>
@Data
@NoArgsConstructor
public class JpaTask extends Task {

    private String additionalAction;

    public JpaTask(String name, int hoursCost, HPerson person, String additionalAction) {
        super(name, hoursCost, person);
        this.additionalAction = additionalAction;
    }

}
// end::main[]
