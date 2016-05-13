package pl.java.scalatech.domain.inheritence.single;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@DiscriminatorValue("cd")
@Data
@NoArgsConstructor
public class JpaTask extends Task{


    private String additionalAction;

    public JpaTask(String name, int hoursCost, Person person,String additionalAction) {
        super(name, hoursCost, person);
        this.additionalAction = additionalAction;
    }






}
