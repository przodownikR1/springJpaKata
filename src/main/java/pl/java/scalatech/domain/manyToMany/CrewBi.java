package pl.java.scalatech.domain.manyToMany;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import pl.java.scalatech.domain.AbstractEntity;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@lombok.ToString(exclude="tanks")
@EqualsAndHashCode(exclude="tanks")
public class CrewBi extends AbstractEntity{

    private static final long serialVersionUID = -1130489364466402054L;
    private int count;

    @ManyToMany(mappedBy="crews")
    private Set<TankBi> tanks = new HashSet<>();

    public void addTank(TankBi tank) {
        tanks.add(tank);
        tank.getCrews().add(this);
        }

}
