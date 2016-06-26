package pl.java.scalatech.interceptor;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import pl.java.scalatech.domain.audit.Book;

public final class HibernateUtils {
	private static SessionFactory buildSessionFactory;
	static {
	    Configuration cfg = new Configuration();
        cfg.configure("hibernate.cfg.xml");
        cfg.setProperty("hibernate.show_sql","true");

        cfg.setProperty("hibernate.generate_statistics","true");
        cfg.addPackage("pl.java.scalatech.domain.audit").addAnnotatedClass(Book.class);

		buildSessionFactory = cfg.buildSessionFactory();
	}

	public static SessionFactory getSessionFactory() {
		return buildSessionFactory;
	}
}