package pl.java.scalatech.interceptor;

import java.io.Serializable;
import java.util.Arrays;

import org.hibernate.CallbackException;
import org.hibernate.EmptyInterceptor;
import org.hibernate.type.Type;

import lombok.extern.slf4j.Slf4j;
@Slf4j
public class LoggingInterceptor extends EmptyInterceptor {
   
    private static final long serialVersionUID = -7265212442467401557L;

    @Override
    public boolean onFlushDirty(Object entity, Serializable id, Object[] currentState, Object[] previousState, String[] propertyNames, Type[] types) throws CallbackException {
        log.info( "Entity {0}#{1} changed from {2} to {3}",
                entity.getClass().getSimpleName(),
                id,
                Arrays.toString( previousState ),
                Arrays.toString( currentState )
        );
        return super.onFlushDirty(entity,id,currentState,previousState,propertyNames,types);
    }
}