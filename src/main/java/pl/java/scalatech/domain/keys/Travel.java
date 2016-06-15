package pl.java.scalatech.domain.keys;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.TableGenerator;

import lombok.Data;

@Entity
@Data
@TableGenerator(name="TRAVEL_GENERATOR",table="GENERATED_KEYS",pkColumnName="PK_COLUMN",valueColumnName="VALUE_COLUMN", pkColumnValue="TRAVEL_ID",allocationSize=10)
public class Travel {
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO, generator="TRAVEL_GENERATOR")
        private int id;
        @Column
        private String name;

}
