package pl.java.scalatech.domain.manyToOne;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

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
@org.hibernate.annotations.Check(
constraints = "AUCTIONSTART < AUCTIONEND"
)
public class Offer extends AbstractEntity{

    private static final long serialVersionUID = 5395878288169119106L;
    @ManyToOne(fetch = FetchType.LAZY) // Defaults to EAGER
    @JoinColumn(name = "ITEM_ID", nullable = false,foreignKey = @ForeignKey(name = "FK_ITEM_ID") )
    private Item item;

    @Column(name="offer_value")
    private BigDecimal value;

    @NotNull
    protected LocalDate auctionStart;
    @NotNull
    protected LocalDate auctionEnd;



}