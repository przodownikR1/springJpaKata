package pl.java.scalatech.domain.oneToMany.table;

import javax.persistence.Entity;

import pl.java.scalatech.domain.AbstractEntity;
@Entity
public class ProductResource extends AbstractEntity {

    private static final long serialVersionUID = -1490554625798967129L;
    private String name;

}
