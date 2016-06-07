package pl.java.scalatech.config;

import java.util.concurrent.TimeUnit;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.codahale.metrics.JmxReporter;
import com.codahale.metrics.MetricRegistry;
import com.codahale.metrics.Slf4jReporter;
import com.codahale.metrics.health.HealthCheckRegistry;
import com.codahale.metrics.logback.InstrumentedAppender;
import com.codahale.metrics.servlets.HealthCheckServlet;
import com.codahale.metrics.servlets.MetricsServlet;
import com.ryantenney.metrics.spring.config.annotation.MetricsConfigurerAdapter;

import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import lombok.extern.slf4j.Slf4j;
@Configuration
@Slf4j
public class MetricsConfig extends MetricsConfigurerAdapter{
   // private static final MetricRegistry METRIC_REGISTRY = new MetricRegistry();
    /*
     *

      @Override
    @Bean
    public MetricRegistry getMetricRegistry() {
        METRIC_REGISTRY.registerAll(new GarbageCollectorMetricSet());
        METRIC_REGISTRY.registerAll(new MemoryUsageGaugeSet());
        METRIC_REGISTRY.registerAll(new ThreadStatesGaugeSet());
        return METRIC_REGISTRY;
    }
*/
    @Override
    public HealthCheckRegistry getHealthCheckRegistry() {
        return new HealthCheckRegistry();
    }

    @Override
    public void configureReporters(MetricRegistry metricRegistry) {
        final LoggerContext factory = (LoggerContext) LoggerFactory.getILoggerFactory();
        final Logger root = factory.getLogger(Logger.ROOT_LOGGER_NAME);

        final InstrumentedAppender metrics = new InstrumentedAppender(metricRegistry);
        metrics.setContext(root.getLoggerContext());
        metrics.start();
        root.addAppender(metrics);
        JmxReporter.forRegistry(metricRegistry).build().start();
        Slf4jReporter reporter = Slf4jReporter.forRegistry(metricRegistry)
                .outputTo(LoggerFactory.getLogger("metrics"))
                .convertRatesTo(TimeUnit.SECONDS)
                .convertDurationsTo(TimeUnit.MILLISECONDS)
                .build();
                reporter.start(1, TimeUnit.MINUTES);
    }


    @Bean
    @Autowired
    public ServletRegistrationBean servletRegistrationBean(MetricRegistry metricRegistry) {
        MetricsServlet ms = new MetricsServlet(metricRegistry);
        ServletRegistrationBean srb = new ServletRegistrationBean(ms, "/stats/*");
        srb.setLoadOnStartup(1);
        return srb;
    }

    @Bean
    @Autowired
    public ServletRegistrationBean servletHealthRegistryBean(HealthCheckRegistry healthCheckRegistry) {
        HealthCheckServlet hc = new HealthCheckServlet(healthCheckRegistry);
        ServletRegistrationBean srb = new ServletRegistrationBean(hc, "/health/*");
        srb.setLoadOnStartup(2);
        return srb;
    }

}
