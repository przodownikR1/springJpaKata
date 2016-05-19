package pl.java.scalatech.domain.oneToMany.normal;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

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
@Table(name = "ITEMS",
indexes = {@Index(name = "IDX_USERNAME", columnList = "ITEM_NAME")}
)
public class Item extends AbstractEntity {

    private static final long serialVersionUID = 5474170031394030929L;
    @Column(name="ITEM_NAME")
    private String name;
    private BigDecimal price;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "ITEM_ID")
    private List<Offer> offers;


}