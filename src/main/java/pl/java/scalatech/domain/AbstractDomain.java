package pl.java.scalatech.domain;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
@MappedSuperclass
@EqualsAndHashCode(of="uuid")
public abstract class AbstractDomain  implements Serializable {

    private static final long serialVersionUID = -1787692913470323899L;

    public static final String GENERATOR_NAME = "idGenerator";

    @Id
    @GeneratedValue(generator=GENERATOR_NAME)
    @Getter
    @Setter
    protected Long id;


    @Basic
    @Column(unique = true, updatable = false, length = 36, columnDefinition = "char(36)")
    protected String uuid;
    @Column(updatable = false)
    protected LocalDateTime created;

    public AbstractDomain() {
        super();
        uuid = UUID.randomUUID().toString();
        created = LocalDateTime.now();
    }
    @Version
    @Getter
    private Long version;
}
