package pl.java.scalatech.domain.audit;

import java.util.Date;

import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@MappedSuperclass
@ToString
public class Auditable {
    @Getter
    @Setter
    @Temporal(TemporalType.TIMESTAMP)
    Date createDate;
}