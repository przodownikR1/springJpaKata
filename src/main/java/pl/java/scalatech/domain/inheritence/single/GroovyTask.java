package pl.java.scalatech.domain.inheritence.single;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@DiscriminatorValue("groovy")
@Data
@NoArgsConstructor
public class GroovyTask extends Task{


    private String writeNewRule;



    public GroovyTask(String name, int hoursCost, Person person,String writeNewRule) {
        super(name, hoursCost, person);
        this.writeNewRule = writeNewRule;
    }



}
