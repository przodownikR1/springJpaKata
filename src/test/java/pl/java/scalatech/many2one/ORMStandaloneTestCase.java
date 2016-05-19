package pl.java.scalatech.many2one;

import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.junit.Before;

import pl.java.scalatech.domain.manyToOne.Item;
import pl.java.scalatech.domain.manyToOne.Offer;

public abstract class ORMStandaloneTestCase {

    protected SessionFactory sf;


	@Before
	public void setup() {
		StandardServiceRegistryBuilder srb = new StandardServiceRegistryBuilder()
			.applySetting( "hibernate.show_sql", "true" )
			.applySetting( "hibernate.format_sql", "true" )
			.applySetting( "hibernate.hbm2ddl.auto", "update" )
			.applySetting( "hibernate.dialect", "org.hibernate.dialect.H2Dialect" )
			.applySetting( "hibernate.connection.driver_class", "org.h2.Driver" )
			.applySetting( "hibernate.connection.url", "jdbc:h2:mem:testdbHibernate" )
			.applySetting( "hibernate.connection.username", "sa" )
			.applySetting( "hibernate.connection.password", "" )
			.applySetting( "hibernate.use_sql_comment", "true" )
			;
		Metadata metadata  = null;
		if(packageBase()== null){
		metadata= new MetadataSources( srb.build() ).addAnnotatedClass(getEntityClass()).buildMetadata();
		}else{
		    metadata= new MetadataSources( srb.build() )
		            .addAnnotatedClass(Item.class)
		            .addAnnotatedClass(Offer.class)

		            .addPackage(packageBase()).buildMetadata();
		}

        sf = metadata.buildSessionFactory();
    }

    abstract protected Class<?> getEntityClass();

    abstract protected String packageBase();




}