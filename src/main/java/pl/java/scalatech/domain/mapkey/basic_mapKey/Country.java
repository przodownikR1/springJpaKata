package pl.java.scalatech.domain.mapkey.basic_mapKey;

import java.util.Map;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.MapKey;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import pl.java.scalatech.domain.AbstractEntity;


@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name="MAP_COUNTRY")
public class Country extends AbstractEntity{

    private static final long serialVersionUID = -7584455870556298977L;

    @Column(name="name")
	private String name;
	
	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name="country_id")
	@MapKey(name="id")
	private Map<Integer,State> states;
	
	public Country(long id,String name,Map<Integer,State> states){
		this.id=id;
		this.name=name;
		this.states=states;
	}
	
}