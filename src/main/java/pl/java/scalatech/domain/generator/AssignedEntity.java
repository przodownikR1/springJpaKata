package pl.java.scalatech.domain.generator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
@Builder
public class AssignedEntity implements Identifiable<Long>{


    @Id
    @GenericGenerator(name = "assigned-identity", strategy = "pl.java.scalatech.domain.generator.impl.IdentifiableImpl")
    @GeneratedValue( generator = "assigned-identity", strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;



}
