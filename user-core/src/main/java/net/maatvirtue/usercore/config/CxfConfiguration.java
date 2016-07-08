package net.maatvirtue.usercore.config;

import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;
import net.maatvirtue.wsutils.filter.CorsFilter;
import net.maatvirtue.wsutils.filter.RequestLogFilter;
import net.maatvirtue.wsutils.restexception.providers.RestExceptionFeature;
import org.apache.cxf.jaxrs.spring.SpringComponentScanServer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(SpringComponentScanServer.class)
public class CxfConfiguration
{
	@Bean
	public RestExceptionFeature restExceptionFeature()
	{
		return new RestExceptionFeature("net.maatvirtue");
	}

	@Bean
	public RequestLogFilter requestLogFilter()
	{
		return new RequestLogFilter();
	}

	@Bean
	public CorsFilter corsFilter()
	{
		return new CorsFilter();
	}

	@Bean
	public JacksonJsonProvider jacksonJsonProvider()
	{
		return new JacksonJsonProvider();
	}
}
