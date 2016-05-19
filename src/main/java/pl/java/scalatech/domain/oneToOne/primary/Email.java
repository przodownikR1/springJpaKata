package pl.java.scalatech.domain.oneToOne.primary;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Table(name="EMAILS")
public class Email{



    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    @Column(name="FROM_MAIL")
    private String from;
    @Column(name="TO_MAIL")
    private String to;
    @Column(name="SUBJECT_MAIL")
    private String topic;
    private LocalDateTime time;

}
