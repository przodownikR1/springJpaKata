package pl.java.scalatech.domain.selfReference;

import static com.google.common.collect.Sets.newHashSet;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import pl.java.scalatech.domain.AbstractEntity;

@Entity
@Table(name="EMPLOYEE_SELF")
@Data
@lombok.EqualsAndHashCode(exclude="manager")
@ToString(exclude="manager")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Employee extends AbstractEntity{
    private static final long serialVersionUID = -5611293337609654205L;

    @Column(name="FIRSTNAME")
    private String firstname;

    @Column(name="LASTNAME")
    private String lastname;

    @ManyToOne(cascade={CascadeType.ALL})
    @JoinColumn(name="manager_id")
    private Employee manager;

    @Column(updatable=false,insertable=false,name="manager_id")
    private Long managerId;

    @OneToMany(mappedBy="manager",cascade={CascadeType.ALL})
    private Set<Employee> employees = newHashSet();

    public Employee(String firstname, String lastname) {
        super();
        this.firstname = firstname;
        this.lastname = lastname;
    }
   public void addEmployee(Employee employee){
       employee.setManager(this);
       getEmployees().add(employee);

   }

}