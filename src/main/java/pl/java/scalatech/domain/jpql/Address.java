package pl.java.scalatech.domain.jpql;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Embeddable
@AllArgsConstructor
@Builder
@Data
public class Address {

    @NotNull
    @Column(nullable = false)
    protected String street;

    @NotNull
    @Column(nullable = false, length = 5)
    protected String zipcode;
    @NotNull
    @Column(nullable = false)
    protected String city;

    protected Address() {
    }

}