package pl.java.scalatech.collection.map.one2many;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
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
@ToString(exclude="item")
@Table(name="MAP_BID")
public class Bid extends AbstractEntity{

    private String name;
    private BigDecimal account;
    
    @ManyToOne
    private Item item;
}
