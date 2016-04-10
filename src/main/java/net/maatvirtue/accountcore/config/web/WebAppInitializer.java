package net.maatvirtue.accountcore.config.web;

import net.maatvirtue.accountcore.constants.Constants;
import net.maatvirtue.accountcore.filter.CorsFilter;
import net.maatvirtue.accountcore.filter.RequestLogFilter;
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
		registerFilters(container);

		container.addServlet("CXF Servlet", new CXFServlet()).addMapping("/*");
	}

	private void setupSpringApplicationContext(ServletContext container)
	{
		AnnotationConfigWebApplicationContext applicationContext = new AnnotationConfigWebApplicationContext();
		applicationContext.scan(Constants.SPRING_CONFIG_PACKAGE);

		container.addListener(new ContextLoaderListener(applicationContext));
	}

	private void registerFilters(ServletContext container)
	{
		container.addFilter("cors", CorsFilter.class).addMappingForUrlPatterns(null, false, "/*");
		container.addFilter("requestLog", RequestLogFilter.class).addMappingForUrlPatterns(null, false, "/*");
	}
}
