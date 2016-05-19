package pl.java.scalatech.domain.oneToOneBi;

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

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name="BI_EMAIL_MESSAGES")
public class EmailMessage_BI extends AbstractEntity{

    private static final long serialVersionUID = 4833517106774781504L;

    @Column(name = "MESSAGE_CONTENT")
    private String content;

    @OneToOne(cascade=CascadeType.ALL)
    private Email_BI email;

}