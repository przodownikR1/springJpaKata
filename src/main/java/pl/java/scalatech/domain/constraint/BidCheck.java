package pl.java.scalatech.domain.constraint;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.java.scalatech.domain.AbstractEntity;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@org.hibernate.annotations.Check(constraints = "BID_START < BID_END")
public  class BidCheck extends AbstractEntity{


    private static final long serialVersionUID = 2054603328460932237L;
    @NotNull

    @Column(name="BID_START")
    protected Date bidStart;
    @NotNull
    @Column(name="BID_END")
    protected Date bidEnd;
}
