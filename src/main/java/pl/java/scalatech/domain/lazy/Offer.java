package pl.java.scalatech.domain.lazy;

import java.math.BigDecimal;

import javax.persistence.Column;
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
@NoArgsConstructor
@Builder
@Table(name="Lazy_OFFER")
public class Offer extends AbstractEntity{

    private static final long serialVersionUID = 4367131894333818343L;
    @Column(name="offer_value")
    private BigDecimal value;



}