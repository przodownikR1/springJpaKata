package pl.java.scalatech.domain.person;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;
import org.hibernate.annotations.Type;
import org.hibernate.validator.constraints.Email;

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


}
