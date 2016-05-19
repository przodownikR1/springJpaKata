package pl.java.scalatech.domain.generator;

import javax.persistence.Entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@ToString(callSuper=true)
@NoArgsConstructor
public class TableGeneratorEntity extends TableGenerator {

    private String tableGenName;

    public TableGeneratorEntity(String name) {
        super();
        tableGenName = name;
    }
}
