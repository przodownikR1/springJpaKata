package pl.java.scalatech.domain.version;

import javax.persistence.Entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.java.scalatech.domain.AbstractEntity;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Book extends AbstractEntity {

    private static final long serialVersionUID = 8278795721222257616L;

    private String title;
    private String name;
}