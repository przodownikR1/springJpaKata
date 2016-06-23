package pl.java.scalatech.domain.mapkey.basic_mapKey;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import pl.java.scalatech.domain.AbstractEntity;


@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name="MAP_STATE")
public class State extends AbstractEntity{
 
    private static final long serialVersionUID = 2596693331819046057L;
    @Column(name = "name")
    private String name;
}
