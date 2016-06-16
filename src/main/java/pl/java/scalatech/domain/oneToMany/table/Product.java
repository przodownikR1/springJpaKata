package pl.java.scalatech.domain.oneToMany.table;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.NaturalId;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.java.scalatech.domain.AbstractEntity;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Product extends AbstractEntity{
    private static final long serialVersionUID = 8172875104670222670L;
    @NaturalId
    private String serialNumber;
    @OneToMany
    @JoinTable(
            name="PRODUCT_PARTS",
            joinColumns = @JoinColumn( name="PRODUCT_ID"),
            inverseJoinColumns = @JoinColumn( name="PART_ID")
    )
    @org.hibernate.annotations.Where(clause = "ACTIVE = 'true'")
    private Set<Part> parts = new HashSet<>();


   @ManyToOne
   @OnDelete(action = OnDeleteAction.CASCADE)
   private ProductCustomer productCustomer;


   @ManyToOne
   @OnDelete(action = OnDeleteAction.CASCADE)
   private ProductResource resource;



}


