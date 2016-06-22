package pl.java.scalatech.domain.mapkey.entityExample;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
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
@Table(name="MAP_COMPANY")
public class Company extends AbstractEntity{

    private static final long serialVersionUID = -9143975818074812878L;

    @OneToMany
	@JoinTable(name = "DEPT_EMP_RESP", joinColumns = @JoinColumn(name = "COMP_ID"), inverseJoinColumns = @JoinColumn(name = "EMP_ID"))
	@MapKeyJoinColumn(name = "DEPT_ID", nullable = true,foreignKey=@ForeignKey(name = "FK_DEPT_PERSON"))	
	private Map<Department, Person> departmentResponsibles = new HashMap<>();

	private String name;
}
