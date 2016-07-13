package pl.java.scalatech.domain.example.n1;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;

import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import pl.java.scalatech.domain.AbstractEntity;
//tag::main[]
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Data
//@BatchSize(size=2) //<1>
@DynamicUpdate(value=true) //<2>
@DynamicInsert(value=true) //<3>
//@Immutable //<4>
public class JobCandidate extends AbstractEntity{

    private static final long serialVersionUID = -7860714163822149386L;
    private String fullName;
    @Column(nullable=true)
    private Integer age ;
    private BigDecimal salary;
    private String a,b,c,w,r,t,y,u;
    public JobCandidate(String fullName) {
        super();
        this.fullName = fullName;
    }
    public JobCandidate(Long key, String fullName) {
        id = key;
        this.fullName = fullName;
    }

}

// end::main[]