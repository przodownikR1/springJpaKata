package pl.java.scalatech.domain.manyToOne;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;

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
public class Item  extends AbstractEntity{

    private static final long serialVersionUID = -2666322967616459295L;
    @Column(name="ITEM_NAME",length=20,unique=true)
    private String name;

    @Column(columnDefinition ="varchar(15) not null unique check (not substring(lower(OWNER), 0, 5) = 'admin')")
    private String owner;

    @Column(nullable=false,scale=2,precision=2)
    private BigDecimal price;


}