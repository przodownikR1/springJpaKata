package pl.java.scalatech.config;

import javax.persistence.EntityManagerFactory;

import org.hibernate.SessionFactory;
import org.hibernate.jpa.HibernateEntityManagerFactory;
import org.hibernate.stat.Statistics;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.EnableMBeanExport;
import org.springframework.jmx.export.MBeanExporter;

import com.google.common.collect.ImmutableMap;

import lombok.extern.slf4j.Slf4j;

@Configuration
@EnableMBeanExport
@Slf4j
public class JmxConfig {

    @Autowired
    private EntityManagerFactory entityManagerFactory;

    @Bean
    @DependsOn("statisticsService")
    public MBeanExporter jmxService(Statistics statistics) {
        MBeanExporter exporter = new MBeanExporter();
        exporter.setBeans(ImmutableMap.of("Hibernate:application=Statistics", (Object) statistics));
        return exporter;
    }



    @Bean
    @DependsOn("entityManagerFactory")
    public Statistics statisticsService(EntityManagerFactory entityManagerFactory) {
        SessionFactory sf = ((HibernateEntityManagerFactory) entityManagerFactory).getSessionFactory();
        return sf.getStatistics();
    }

}
