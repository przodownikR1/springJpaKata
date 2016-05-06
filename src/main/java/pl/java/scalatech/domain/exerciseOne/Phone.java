package pl.java.scalatech.domain.exerciseOne;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.java.scalatech.domain.AbstractEntity;

@Entity
@Table(name = "PHONE")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Phone extends AbstractEntity{


    private static final long serialVersionUID = -2151027066005229271L;
    @Column(name = "NUMBER")
    private String number;
    @Column(name = "TYPE")
    @Enumerated
    private PhoneType type;
    @ManyToOne
    @JoinColumn(name = "EMPLOYEE_ID")
    private Employee employee;
}