package pl.java.scalatech.domain;

import javax.persistence.PostPersist;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import lombok.extern.slf4j.Slf4j;
import pl.java.scalatech.domain.mainPerson.Person;
@Slf4j
public class SampleListener {
    @PreUpdate
    @PostPersist
    @PrePersist
    public void setLastUpdate( Person p ) {
        if("addAction".equals(p.getFirstname())){
        p.setActive(true);}

        log.info("person after set active on true , before save action : {}",p);
    }
}
