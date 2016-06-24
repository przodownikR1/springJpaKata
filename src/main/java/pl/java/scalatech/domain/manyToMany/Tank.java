package pl.java.scalatech.domain.manyToMany;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.UniqueConstraint;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.java.scalatech.domain.AbstractEntity;
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class  Tank extends AbstractEntity{

    /**
     *
     */
    private static final long serialVersionUID = -3544719599486408375L;
    private String name;
    private String serialNumber;


    @ManyToMany(fetch=FetchType.EAGER,cascade=CascadeType.ALL)
    
    @JoinTable (
    name="Tank_Crew",
    joinColumns = {@JoinColumn(name="Tank_ID")},
    inverseJoinColumns={@JoinColumn(name="CREW_ID")}
    ,foreignKey = @ForeignKey(name = "TANK_CREW_FK")
    ,uniqueConstraints = @UniqueConstraint(columnNames={"CREW_ID", "Tank_ID"}))
    private Set<Crew> crews = new HashSet<>();
}
