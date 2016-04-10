package net.maatvirtue.accountcore.config.web;

import net.maatvirtue.accountcore.config.spring.SpringApplicationConfiguration;
import net.maatvirtue.accountcore.config.spring.SpringRootContextConfig;
import net.maatvirtue.accountcore.constants.Constants;
import net.maatvirtue.accountcore.filter.CorsFilter;
import net.maatvirtue.accountcore.filter.RequestLogFilter;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletRegistration;

public class WebAppInitializer implements WebApplicationInitializer
{
	@Override
	public void onStartup(ServletContext container)
	{
		setupSpringRootApplicationContext(container);
		setupSpringDispatcherServlet(container);
		registerFilters(container);
	}

	private void setupSpringRootApplicationContext(ServletContext container)
	{
		AnnotationConfigWebApplicationContext rootContext = new AnnotationConfigWebApplicationContext();
		rootContext.register(SpringRootContextConfig.class);

		container.addListener(new ContextLoaderListener(rootContext));
	}

	private void setupSpringDispatcherServlet(ServletContext container)
	{
		AnnotationConfigWebApplicationContext dispatcherServlet = new AnnotationConfigWebApplicationContext();
		dispatcherServlet.register(SpringApplicationConfiguration.class);

		String servletName = "account-webservice-v" + Constants.WEB_SERVICE_MAJOR_VERSION;

		ServletRegistration.Dynamic dispatcher = container.addServlet(servletName, new DispatcherServlet(dispatcherServlet));
		dispatcher.setLoadOnStartup(1);
		dispatcher.addMapping("/" + Constants.WEB_SERVICE_MAJOR_VERSION + "/*");
	}

	private void registerFilters(ServletContext container)
	{
		container.addFilter("cors", CorsFilter.class).addMappingForUrlPatterns(null, false, "/*");
		container.addFilter("requestLog", RequestLogFilter.class).addMappingForUrlPatterns(null, false, "/*");
	}
}
