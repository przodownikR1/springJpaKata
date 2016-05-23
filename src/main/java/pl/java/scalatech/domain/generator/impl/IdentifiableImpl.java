package pl.java.scalatech.domain.generator.impl;

import java.io.Serializable;
import java.util.concurrent.atomic.AtomicLong;

import javax.persistence.EntityManager;

import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.id.IdentityGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import pl.java.scalatech.domain.generator.Identifiable;

@Component
@Slf4j
@NoArgsConstructor
public class IdentifiableImpl extends IdentityGenerator implements Identifiable<Long>{

    private AtomicLong aLong = new AtomicLong();

    private  EntityManager entityManager;

    @Override
    public Serializable generate(SessionImplementor session, Object obj) {
        if(obj instanceof Identifiable) {
            Identifiable identifiable = (Identifiable) obj;
            //Serializable id = identifiable.getId(); //TODO
            Serializable id = getId();
            if(id != null) {
                return id;
            }
        }
        return super.generate(session, obj);
    }

    @Autowired
    public IdentifiableImpl(EntityManager entityManager) {
    this.entityManager = entityManager;
    }

    @Override
    public Long getId() {
        long id = aLong.incrementAndGet();
        log.info("id :  {}",id);
        return id;
    }

}
