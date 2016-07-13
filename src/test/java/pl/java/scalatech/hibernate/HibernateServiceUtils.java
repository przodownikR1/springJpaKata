package pl.java.scalatech.hibernate;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import lombok.extern.slf4j.Slf4j;
import pl.java.scalatech.domain.mainPerson.Person;

@Slf4j
public final class HibernateServiceUtils {

    private static SessionFactory sf;

    public static SessionFactory getSessionFactory() {
        log.info("+++ getSessionFactory");
        if (sf == null) {
            Configuration cfg = new Configuration();
            cfg.configure("hibernate.cfg.xml");
            // @formatter:off
            cfg.addPackage("pl.java.scalatech.domain.mainPerson")
              .addAnnotatedClass(Person.class);
            // @formatter:on
            cfg.setProperty("hibernate.generate_statistics","true");
            
            ServiceRegistry serviceRegistry = 
                    new StandardServiceRegistryBuilder().applySettings(cfg.getProperties()).build();
            
            sf = cfg.buildSessionFactory(serviceRegistry);
        }

        return sf;

    }


    public static SessionFactory getSessionFactory(Class[] clazz) {
        log.info("+++ getConcreteEntitySessionFactory");
        if (sf == null) {
            Configuration cfg = new Configuration();
            cfg.configure("hibernate.cfg.xml");
            // @formatter:off
            for (Class cl : clazz) {
                log.info("+++     mapped entity => {}",cl);
                cfg.addPackage("pl.java.scalatech.domain").addAnnotatedClass(cl);
            }
            // @formatter:on
            cfg.setProperty("hibernate.generate_statistics","true");
            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(cfg.getProperties()).build();
            sf = cfg.buildSessionFactory(serviceRegistry);
        }

        return sf;

    }

}
