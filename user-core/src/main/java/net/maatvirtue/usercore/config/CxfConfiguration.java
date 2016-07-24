package net.maatvirtue.usercore.config;

import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;
import net.maatvirtue.wsutils.filter.CorsFilter;
import net.maatvirtue.wsutils.filter.RequestLogFilter;
import net.maatvirtue.wsutils.restexception.providers.RestExceptionFeature;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
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
