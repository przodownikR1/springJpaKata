package pl.java.scalatech.domain.example.n1;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import pl.java.scalatech.domain.AbstractEntity;
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Data
//@BatchSize(size=5)
@DynamicUpdate(value=true)
@DynamicInsert(value=true)
//@Immutable
public class JobCandidate extends AbstractEntity{

    private static final long serialVersionUID = -7860714163822149386L;
    private String fullName;
    @Column(nullable=true)
    private Integer age ;
    private BigDecimal salary;
    public JobCandidate(String fullName) {
        super();
        this.fullName = fullName;
    }
    public JobCandidate(Long key, String fullName) {
        id = key;
        this.fullName = fullName;
    }

}
