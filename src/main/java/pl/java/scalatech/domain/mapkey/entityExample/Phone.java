package pl.java.scalatech.domain.mapkey.entityExample;

import javax.persistence.Entity;
import javax.persistence.Table;

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
@Table(name="MAP_PHONE")
public class Phone extends AbstractEntity{
    
    private static final long serialVersionUID = 5941765769329870761L;
    private String phoneNumber;
   
}
