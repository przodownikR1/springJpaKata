package pl.java.scalatech.domain.exerciseOne;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.java.scalatech.domain.AbstractEntity;

@Entity
@Table(name = "PROJECT_EXERCISE")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Project extends AbstractEntity{

    @Column(name = "NAME")
    protected String name;
    @ManyToMany
    @JoinTable(
            name = "PROJECT_EMPLOYEE",
            joinColumns = @JoinColumn(name = "EMPLOYEES_ID"),
            inverseJoinColumns = @JoinColumn(name = "PROJECTS_ID")
    )
    protected Collection<Employee> employees = new ArrayList<>();
}
