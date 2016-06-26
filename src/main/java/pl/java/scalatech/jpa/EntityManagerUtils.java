package pl.java.scalatech.jpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EntityManagerUtils {

    static EntityManagerFactory managerFactory;

    public static EntityManager getEntityManager() {
        if (managerFactory == null) {
            managerFactory = Persistence.createEntityManagerFactory("PU");
        }
        EntityManager manager = managerFactory.createEntityManager();
        return manager;
    }
}
