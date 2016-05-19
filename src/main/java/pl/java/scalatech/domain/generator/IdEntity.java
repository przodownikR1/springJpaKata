package pl.java.scalatech.domain.generator;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.hibernate.annotations.GenericGenerator;

import lombok.Getter;
import lombok.ToString;

@MappedSuperclass
@ToString
public abstract class IdEntity {

    @GeneratedValue(generator = "hibernate-uuid")
    @GenericGenerator(name = "hibernate-uuid", strategy = "uuid")
    @Getter
    @Id
	protected String id;

}