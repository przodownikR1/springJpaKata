package pl.java.scalatech.domain.exerciseOne;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.java.scalatech.domain.AbstractEntity;

@Entity
@Table(name = "EMPLOYEE_EXERCISE")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Employee extends AbstractEntity{

    private static final long serialVersionUID = -6882527852158059488L;
    private String name;
    private long salary;
    @Temporal(TemporalType.DATE)
    private Date startDate;

    @OneToOne
    private Address address;

    @OneToMany(mappedBy="employee")
    private Collection<Phone> phones = new ArrayList<>();

    @ManyToOne
    private Department dept;

    @ManyToOne
    private Employee manager;

    @OneToMany(mappedBy="manager")
    private Collection<Employee> directs = new ArrayList<>();

    @ManyToMany(mappedBy="employees")
    private Collection<Project> projects = new ArrayList<>();
}