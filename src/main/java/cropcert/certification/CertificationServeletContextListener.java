/**
 * 
 */
package cropcert.certification;

import java.io.IOException;
import java.net.URISyntaxException;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletContextEvent;

import org.glassfish.jersey.servlet.ServletContainer;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.Guice;
import com.google.inject.Injector;

import com.google.inject.Scopes;
import com.google.inject.servlet.GuiceServletContextListener;
import com.google.inject.servlet.ServletModule;
import com.strandls.user.controller.UserServiceApi;

import cropcert.certification.controller.ControllerModule;
import cropcert.certification.dao.DaoModule;
import cropcert.certification.service.ServiceModule;
import cropcert.entities.api.UserApi;

/**
 * 
 * @author vilay
 *
 */
public class CertificationServeletContextListener extends GuiceServletContextListener {

	private final Logger logger = LoggerFactory.getLogger(CertificationServeletContextListener.class);

	@Override
	protected Injector getInjector() {

		return Guice.createInjector(new ServletModule() {
			@Override
			protected void configureServlets() {
				Configuration configuration = new Configuration();

				try {
					for (Class<?> cls : ApplicationConfig.getSwaggerAnnotationClassesFromPackage("cropcert")) {
						configuration.addAnnotatedClass(cls);
					}
				} catch (ClassNotFoundException | IOException | URISyntaxException e) {
					logger.error(e.getMessage());
				}

				configuration = configuration.configure();
				SessionFactory sessionFactory = configuration.buildSessionFactory();

				ObjectMapper objectMapper = new ObjectMapper();
				bind(ObjectMapper.class).toInstance(objectMapper);
				bind(SessionFactory.class).toInstance(sessionFactory);

				bind(UserApi.class).in(Scopes.SINGLETON);

				bind(UserServiceApi.class).in(Scopes.SINGLETON);

				bind(ServletContainer.class).in(Scopes.SINGLETON);

				Map<String, String> props = new HashMap<>();
				props.put("javax.ws.rs.Application", ApplicationConfig.class.getName());
				props.put("jersey.config.server.provider.packages", "cropcert");
				props.put("jersey.config.server.wadl.disableWadl", "true");

				serve("/api/*").with(ServletContainer.class, props);
			}
		}, new ControllerModule(), new DaoModule(), new ServiceModule());
	}

	@Override
	public void contextDestroyed(ServletContextEvent servletContextEvent) {

		Injector injector = (Injector) servletContextEvent.getServletContext().getAttribute(Injector.class.getName());

		SessionFactory sessionFactory = injector.getInstance(SessionFactory.class);
		sessionFactory.close();

		super.contextDestroyed(servletContextEvent);
		// ... First close any background tasks which may be using the DB ...
		// ... Then close any DB connection pools ...

		// Now deregister JDBC drivers in this context's ClassLoader:
		// Get the webapp's ClassLoader
		ClassLoader cl = Thread.currentThread().getContextClassLoader();
		// Loop through all drivers
		Enumeration<Driver> drivers = DriverManager.getDrivers();
		while (drivers.hasMoreElements()) {
			Driver driver = drivers.nextElement();
			if (driver.getClass().getClassLoader() == cl) {
				// This driver was registered by the webapp's ClassLoader, so deregister it:
				try {
					logger.info("Deregistering JDBC driver {}", driver);
					DriverManager.deregisterDriver(driver);
				} catch (SQLException ex) {
					logger.error("Error deregistering JDBC driver {}", driver, ex);
				}
			} else {
				// driver was not registered by the webapp's ClassLoader and may be in use
				// elsewhere
				logger.trace("Not deregistering JDBC driver {} as it does not belong to this webapp's ClassLoader",
						driver);
			}
		}
	}
}
