package pl.java.scalatech.domain.bags;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.MapKeyColumn;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.java.scalatech.domain.AbstractEntity;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserMap extends AbstractEntity{

    @ElementCollection

    @CollectionTable(name = "PHONES")

    @MapKeyColumn(name = "PHONE_NUM")
    @Column(name = "NUM")
    private Map<String, String> phones = new HashMap<String, String>();

}
