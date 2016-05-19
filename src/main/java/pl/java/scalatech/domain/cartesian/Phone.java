package pl.java.scalatech.domain.cartesian;

import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.java.scalatech.domain.AbstractEntity;

@Entity
@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
@Table(name="CARTESIAN_PHONE")
public class Phone extends AbstractEntity{


    private String phoneNumber;
}
