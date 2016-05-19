package pl.java.scalatech.domain.bags;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;

import pl.java.scalatech.domain.AbstractEntity;

@Entity
public class Item extends AbstractEntity{
    @ElementCollection
    @CollectionTable(name = "IMAGE")
   /* @Column(name = "FILENAME")
    @org.hibernate.annotations.CollectionId(columns = @Column(name = "IMAGE_ID") ,
    type = @org.hibernate.annotations.Type(type = "long") , generator = Constants.ID_GENERATOR)*/

    private Collection<String> images = new ArrayList<String>();
}