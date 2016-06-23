package pl.java.scalatech.domain.bags;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.MapKeyColumn;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.java.scalatech.domain.AbstractEntity;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Table(name="MAP_USER_MAP")
public class UserMap extends AbstractEntity{

    @ElementCollection
    @CollectionTable(name = "MAP_PHONES")
    @MapKeyColumn(name = "PHONE_OWNER")
    @Column(name = "NUM")
    private Map<String, String> phones = new HashMap<>();
    private String name;

}
