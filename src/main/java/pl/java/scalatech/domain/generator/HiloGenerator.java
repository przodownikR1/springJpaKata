package pl.java.scalatech.domain.generator;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import lombok.Getter;
import lombok.ToString;

@MappedSuperclass
@ToString
public abstract class HiloGenerator {
    // @formatter:off
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "hilo_sequence_generator")
    @GenericGenerator(name = "hilo_sequence_generator", strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
    parameters = { @Parameter(name = "sequence_name", value = "hilo_seqeunce"),
            @Parameter(name = "initial_value", value = "1"),
            @Parameter(name = "increment_size", value = "10"),
            @Parameter(name = "optimizer", value = "hilo") })
    // @formatter:on
    @Id
    @Getter
    protected Long id;

}


/** default SequenceStyleGenerator and TableGenerator use pooled optimizer**/
/* hibernate.id.optimizer.pooled.prefer_lo=true*/

/*

@Id
@GenericGenerator(name = "sequenceGenerator", strategy = "enhanced-sequence",
        parameters = {
                @org.hibernate.annotations.Parameter(name = "optimizer",
                        value = "pooled-lo"
                ),
                @org.hibernate.annotations.Parameter(name = "initial_value", value = "1"),
                @org.hibernate.annotations.Parameter(name = "increment_size", value = "10")
        }
)
@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
*/
/*
@Id
@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pooled")
@GenericGenerator(
name = "pooled",
strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
parameters = {
@Parameter(name = "sequence_name", value = "sequence"),
@Parameter(name = "initial_value", value = "1"),
@Parameter(name = "increment_size", value = "100"),
@Parameter(name = "optimizer", value = "pooled")
}
)
private Long id;
*/

/*
none: 
hi/lo: 
pooled: 
pooled-lo:
*/