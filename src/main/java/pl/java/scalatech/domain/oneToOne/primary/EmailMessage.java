package pl.java.scalatech.domain.oneToOne.primary;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name="EMAIL_MESSAGES")
public class EmailMessage {

    private static final long serialVersionUID = 4833517106774781504L;


    @Id
    @GeneratedValue(strategy=GenerationType.TABLE)
    protected Long id;


    @Column(name = "MESSAGE_CONTENT")
    private String content;

    @OneToOne(fetch = FetchType.LAZY,optional = false,cascade=CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private Email email;

    public EmailMessage(String content, Email email) {
        super();
        this.content = content;
        this.email = email;
    }




}