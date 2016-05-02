package net.maatvirtue.usercore.config.web;

import net.maatvirtue.usercore.constants.Constants;
import net.maatvirtue.wsutils.restexception.impl.RestExceptionScanner;
import org.apache.cxf.transport.servlet.CXFServlet;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;

import javax.servlet.ServletContext;

public class WebAppInitializer implements WebApplicationInitializer
{
	@Override
	public void onStartup(ServletContext container)
	{
		setupSpringApplicationContext(container);
		addCxfServlet(container);
	}

	private void addCxfServlet(ServletContext container) {
		String servletUrlMapping = "/" + Constants.WEB_SERVICE_MAJOR_VERSION + "/*";

		container.addServlet("CXF Servlet", new CXFServlet()).addMapping(servletUrlMapping);
	}

	private void setupSpringApplicationContext(ServletContext container)
	{
		AnnotationConfigWebApplicationContext applicationContext = new AnnotationConfigWebApplicationContext();
		applicationContext.scan(Constants.SPRING_CONFIG_PACKAGE);

		setupRestException();

		container.addListener(new ContextLoaderListener(applicationContext));
	}

	private void setupRestException()
	{
		new RestExceptionScanner("net.maatvirtue").scan();
	}
}
