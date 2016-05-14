package pl.java.scalatech.domain;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.ZonedDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;
import org.hibernate.annotations.ParamDef;
import org.hibernate.annotations.Type;
import org.hibernate.validator.constraints.Email;
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
@FilterDef(name = "byStatus", parameters = @ParamDef(name = "status", type = "boolean"))
@Filter(name = "byStatus", condition = "active = :status")

public class Person implements Serializable{

    private static final long serialVersionUID = 5279859664147821207L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotNull
    @Size(min=5,max=50)
    private  String firstname;
    @Email
    private  String email;

    private LocalDate modify;
    @PrePersist
    @PreUpdate
    public void init(){
        modify = LocalDate.now();
    }

    @Column(insertable=true,updatable=false)
    @Generated(GenerationTime.INSERT)
    private LocalDate effectiveModify;

    @Type(type="yes_no")
    private Boolean disable;

  /* @Formula("select concat(p.email, ' : ' ,p.firstname ) From Person p")
    private String overview;*/
/*
    @Formula("select count(*) From Person p WHERE p.active = 'true'")
    private int allUser;*/

   /* @ColumnTransformer(read="km * 1.6",write= "? / 1.6")*/
    private Double km;

    private Boolean active;

    @DateTimeFormat(pattern = "dd/MM/yy")

    //@Past
    private ZonedDateTime birthDay;
    //TODO
    //private MonetaryAmount basePrice;

}
