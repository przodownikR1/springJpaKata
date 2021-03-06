package pl.java.scalatech.domain.mainPerson;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.ZonedDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.ColumnTransformer;
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.Formula;
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
import pl.java.scalatech.domain.SampleListener;

@Entity
@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
//tag::main[]
@FilterDef(name = "byStatus", parameters = @ParamDef(name = "status", type = "boolean"))//<1>
@Filter(name = "byStatus", condition = "active = :status")//<2>
@EntityListeners( SampleListener.class )
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

    @Type(type="yes_no") //<3>
    private Boolean disable;

    //TODO
  //@Formula("concat(disable, ' : ' ,active )")/// ? filter problem
    private String overview;

    @Formula("select count(*) From Person p WHERE p.active = 'true'") //<4>
    private int allUser;



    @ColumnTransformer(read="km * 1.6",write= "? / 1.6") //<5>
    private Double km;

    private Boolean active;

    @DateTimeFormat(pattern = "dd/MM/yy")

    //@Past
    private ZonedDateTime birthDay;
    //TODO
    //private MonetaryAmount basePrice;
    @Version
    private long version;
}
// end::main[]
