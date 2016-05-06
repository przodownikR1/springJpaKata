package pl.java.scalatech.domain.example;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import pl.java.scalatech.domain.AbstractEntity;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
//@ToString
public class Cart extends AbstractEntity{

    private String name;
    @OneToMany(cascade=CascadeType.ALL)
    @OrderBy("name desc")
    @JoinColumn(name="cartId")
    private List<SimpleProduct> products;
    
}
