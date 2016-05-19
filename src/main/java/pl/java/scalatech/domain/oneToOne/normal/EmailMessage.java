package pl.java.scalatech.domain.oneToOne.normal;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.java.scalatech.domain.AbstractEntity;
import pl.java.scalatech.domain.oneToOne.Email;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name="EMAIL_MESSAGES")
public class EmailMessage extends AbstractEntity{

    private static final long serialVersionUID = 4833517106774781504L;

    @Column(name = "MESSAGE_CONTENT")
    private String content;

    @OneToOne(cascade=CascadeType.ALL)
    private Email email;

}