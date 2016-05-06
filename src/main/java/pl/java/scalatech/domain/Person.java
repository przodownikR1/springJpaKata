package pl.java.scalatech.domain;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.ZonedDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class Person implements Serializable{

    private static final long serialVersionUID = 5279859664147821207L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private  String firstname;
    private  String email;

    private LocalDate modify;

    @DateTimeFormat(pattern = "dd/MM/yy")
    @NotNull
    //@Past
    private ZonedDateTime birthDay;
    //TODO
    //private MonetaryAmount basePrice;

}
