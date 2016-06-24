package pl.java.scalatech.domain.inheritence.single;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

// tag::main[]
@Entity
@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
@Table(name = "HelpPerson")
public class HPerson implements Serializable { 

    private static final long serialVersionUID = 5279859664147821207L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String firstname;
    private String email;

}
// end::main[]
