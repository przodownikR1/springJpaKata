package pl.java.scalatech.domain.sql;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.NamedNativeQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Loader;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.java.scalatech.domain.AbstractEntity;

@Entity
@Table(name="weapons")
@Data
@AllArgsConstructor
@NoArgsConstructor
/*@SQLInsert( sql="INSERT INTO weapons(name, quantity, symbol, id) VALUES(upper(?),?,?,?)")
@SQLUpdate( sql="UPDATE WEAPONS SET name = upper(?), symbol = ?, quantity = ? WHERE id = ?")
@SQLDelete( sql="DELETE WEAPONS WHERE id = ?")
@SQLDeleteAll( sql="DELETE WEAPONS")*/
//TODO
@Loader(namedQuery = "weaponSQL")
@NamedNativeQuery(name="weaponSQL", query="select id, quantity, name, lower( symbol ) as symbol from WEAPONS where id= ?", resultClass = Weapon.class)
public class Weapon extends AbstractEntity{

    private static final long serialVersionUID = 826248587465637699L;
    @Column(name="quantity")
	private Long quantity;

	private String name;

	@Column(name="symbol")
	private String symbol;

	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name="weapon_fk")
	//@SQLInsert( sql="UPDATE parts SET weapons_fk = ? where id = ?")
	//@SQLDelete( sql="UPDATE parts SET weapons_fk = null where id = ?")
	private Set<Part> parts = new HashSet<>();

}
