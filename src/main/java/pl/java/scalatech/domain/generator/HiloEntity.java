package pl.java.scalatech.domain.generator;

import javax.persistence.Entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@ToString(callSuper=true)
@NoArgsConstructor
public class HiloEntity extends HiloGenerator{


    private String hiloName;

    public HiloEntity(String hiloName) {
        super();
        this.hiloName = hiloName;
    }

}
