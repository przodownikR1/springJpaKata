package pl.java.scalatech.interceptor;

import org.hibernate.HibernateException;
import org.hibernate.event.internal.DefaultLoadEventListener;
import org.hibernate.event.spi.LoadEvent;
import org.hibernate.event.spi.LoadEventListener;

import lombok.extern.slf4j.Slf4j;
@Slf4j
public class SecuredLoadEntityListener extends DefaultLoadEventListener {
    @Override
    public void onLoad(
            final LoadEvent event,
            final LoadEventListener.LoadType loadType) throws HibernateException {

        log.info("+++++++++++++");
    }
}