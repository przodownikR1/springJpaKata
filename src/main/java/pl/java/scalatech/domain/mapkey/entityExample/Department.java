package pl.java.scalatech.domain.mapkey.entityExample;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.MapKey;
import javax.persistence.MapKeyColumn;
import javax.persistence.MapKeyJoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.java.scalatech.domain.AbstractEntity;


@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name="MAP_DEPT")
public class Department extends AbstractEntity {

    private static final long serialVersionUID = -7670935289254672108L;

    private String name;
    private Long ids;

    @OneToMany(cascade=CascadeType.ALL)    
    @JoinTable(name="DeptPersonMaps")
    @MapKeyJoinColumn(table="deptPersonMaps",name="age")
    private Map<String,Person> persons = new HashMap<>();
    

    @OneToMany(cascade = CascadeType.ALL)
    @MapKey
    Map<UUID, Phone> phones;
    
    @ElementCollection
    @CollectionTable(name = "subDept")
    @MapKeyColumn(name = "subDeptName")
    @Column(name = "subDeptShortName")
    protected Map<String, String> subDepts = new HashMap<>();
    
    
    @ElementCollection
    @CollectionTable(name = "MAP_TASKS")
    protected Map<Responsibility, Task> tasks = new HashMap<>();
}
