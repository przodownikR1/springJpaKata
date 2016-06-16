package pl.java.scalatech.domain.mapkey;

import java.util.Map;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.MapKey;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.java.scalatech.domain.AbstractEntity;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomSkill  extends AbstractEntity implements Skill {

  private String name;

  @OneToMany(cascade = CascadeType.ALL)
  @MapKey(name = "actionType")
  private Map<String, CustomPeference> preference;
}
