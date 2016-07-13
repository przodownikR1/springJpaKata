package pl.java.scalatech.domain.lazy;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.NamedAttributeNode;
import javax.persistence.NamedEntityGraph;
import javax.persistence.NamedEntityGraphs;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.java.scalatech.domain.AbstractEntity;
//tag::main[]
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "Lazy_ITEMS")//indexes = {@Index(name = "IDX_USERNAME", columnList = "ITEM_NAME")
@DynamicInsert //<1>
@NamedEntityGraphs({ //<2>
    @NamedEntityGraph(name = "offers", attributeNodes = { @NamedAttributeNode("offers") })
    })
public class Item extends AbstractEntity {

    private static final long serialVersionUID = 5474170031394030929L;
    @Column(name="ITEM_NAME")
    private String name;
    private BigDecimal price;

    @OneToMany //<3>
    @JoinColumn(name = "ITEM_ID")
    @LazyCollection(LazyCollectionOption.EXTRA)
    private List<Offer> offers;

}
// end::main[]