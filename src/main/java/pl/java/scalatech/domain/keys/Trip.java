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
public class Trip{

    @TableGenerator(name="TRIP_GENERATOR",table="GENERATED_KEYS", pkColumnName="PK_COLUMN", valueColumnName="VALUE_COLUMN", pkColumnValue="TRIP_ID", allocationSize=1)
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator="TRIP_GENERATOR")
    private int id;

    @Column
    private String name;
}