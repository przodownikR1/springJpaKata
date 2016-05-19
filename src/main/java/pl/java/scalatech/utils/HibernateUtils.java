package pl.java.scalatech.utils;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import pl.java.scalatech.domain.Item;

public final class HibernateUtils {
	private static SessionFactory buildSessionFactory;
	static {
	    Configuration cfg = new Configuration();
        cfg.configure("hibernate.cfg.xml");
        cfg.setProperty("hibernate.show_sql","true");

        cfg.setProperty("hibernate.generate_statistics","true");
        cfg.addPackage("pl.java.scalatech.domain").addAnnotatedClass(Item.class);

		buildSessionFactory = cfg.buildSessionFactory();
	}

	public static SessionFactory getSessionFactory() {
		return buildSessionFactory;
	}
}