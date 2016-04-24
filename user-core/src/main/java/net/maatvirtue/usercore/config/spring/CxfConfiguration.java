package net.maatvirtue.usercore.config.spring;

import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;
import net.maatvirtue.wsutils.restexception.providers.RestExceptionBodyReader;
import net.maatvirtue.wsutils.restexception.providers.RestExceptionCxfClientMapper;
import net.maatvirtue.wsutils.restexception.providers.RestExceptionMapper;
import org.apache.cxf.jaxrs.spring.SpringComponentScanServer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(SpringComponentScanServer.class)
public class CxfConfiguration
{
	@Bean
	public RestExceptionMapper restExceptionMapper()
	{
		return new RestExceptionMapper();
	}

	@Bean
	public RestExceptionBodyReader restExceptionBodyReader()
	{
		return new RestExceptionBodyReader();
	}

	@Bean
	public RestExceptionCxfClientMapper restExceptionCxfClientMapper()
	{
		return new RestExceptionCxfClientMapper();
	}

	@Bean
	public JacksonJsonProvider jacksonJsonProvider()
	{
		return new JacksonJsonProvider();
	}
}
