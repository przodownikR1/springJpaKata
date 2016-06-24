package pl.java.scalatech.collection.map.one2many;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.MapKey;
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
@Table(name="MAP_ITEM")
public class Item extends AbstractEntity{
@MapKey(name = "id")
@OneToMany(mappedBy = "item")
protected Map<Long, Bid> bids = new HashMap<>();

}