package pl.java.scalatech.domain.inheritence.single;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.NoArgsConstructor;
import pl.java.scalatech.domain.AbstractEntity;


@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "task_type")
@Table(name="tasks")
@NoArgsConstructor
public abstract class Task extends AbstractEntity{

    private String name;
    private int hoursCost;

    @ManyToOne(cascade=CascadeType.PERSIST)
    @JoinColumn(name="personId")
    private HPerson person;


    public Task(String name, int hoursCost, HPerson person) {
        super();
        this.name = name;
        this.hoursCost = hoursCost;
        this.person = person;
    }





}
