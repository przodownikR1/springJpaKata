package pl.java.scalatech.domain.mapkey.basic_collection;

import javax.persistence.Entity;

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
public class Book extends AbstractEntity{

    private String name;
    private String isbn;
}
