package pl.java.scalatech.domain.oneToOneBi;

import java.time.LocalDateTime;

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
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Table(name="BI_EMAILS")
public class Email_BI extends AbstractEntity{

    private static final long serialVersionUID = -6813717332835182126L;
    @Column(name="FROM_MAIL")
    private String from;
    @Column(name="TO_MAIL")
    private String to;
    @Column(name="SUBJECT_MAIL")
    private String topic;
    private LocalDateTime time;

    @OneToOne(mappedBy = "email")
    EmailMessage_BI email;

}
