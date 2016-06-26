package pl.java.scalatech.interceptor;

import java.io.Serializable;
import java.util.Date;

import org.hibernate.EmptyInterceptor;
import org.hibernate.type.Type;

import pl.java.scalatech.domain.audit.Auditable;

public class AuditableInterceptor extends EmptyInterceptor {
    @Override
    public boolean onSave(Object entity, Serializable id, Object[] state, String[] propertyNames, Type[] types) {
        if (entity instanceof Auditable) {
            for (int i = 0; i < propertyNames.length; i++) {
                if ("createDate".equals(propertyNames[i])) {
                    state[i] = new Date();
                    return true;
                }
            }
        }
        return false;
    }
}