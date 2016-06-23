package pl.java.scalatech.domain.mapkey.basic_collection;

import static com.google.common.collect.Maps.newHashMap;

import java.util.Map;

import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;

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
public class BookStore extends AbstractEntity{

    private String address;
    @ElementCollection  
    @CollectionTable(joinColumns=@JoinColumn(name="bookStoreId"))
    Map<Book,Long> invertory = newHashMap();
   
 
}
