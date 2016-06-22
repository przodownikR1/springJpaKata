package pl.java.scalatech.domain.mapkey.entityExample;

import javax.persistence.Embeddable;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name="MAP_RESPONSIBILITY")
public class Responsibility {

    private String skill;
    
    private int priority;
}
