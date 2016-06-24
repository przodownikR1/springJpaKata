package pl.java.scalatech.domain.inheritence.single;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

//tag::main[]
@Entity
@DiscriminatorValue("java") //<1>
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class JavaTask extends Task{

    private String refactoringName;

    public JavaTask(String name, int hoursCost, HPerson person ,String refactoringName) {
        super(name, hoursCost, person);
        refactoringName = refactoringName;
    }

}
// end::main[]
