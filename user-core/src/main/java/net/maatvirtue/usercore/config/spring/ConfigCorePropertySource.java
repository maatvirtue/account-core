package net.maatvirtue.usercore.config.spring;

import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;
import net.maatvirtue.configcore.api.webservice.ConfigWebService;
import net.maatvirtue.wsutils.restexception.exception.NotFoundRestException;
import net.maatvirtue.wsutils.restexception.providers.RestExceptionBodyReader;
import net.maatvirtue.wsutils.restexception.providers.RestExceptionCxfClientMapper;
import net.maatvirtue.wsutils.restexception.providers.RestExceptionMapper;
import org.apache.cxf.jaxrs.client.JAXRSClientFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.PropertySource;

import javax.ws.rs.client.ClientRequestContext;
import javax.ws.rs.client.ClientRequestFilter;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class ConfigCorePropertySource extends PropertySource<ConfigWebService>
{
	private static class ConfigRequestLogger implements ClientRequestFilter
	{
		private static final Logger logger = LoggerFactory.getLogger(ConfigRequestLogger.class);

		@Override
		public void filter(ClientRequestContext requestContext) throws IOException
		{
			logger.debug("Config request: " + requestContext.getMethod() + " " + requestContext.getUri());
		}
	}

	private static final Logger logger = LoggerFactory.getLogger(ConfigCorePropertySource.class);

	public ConfigCorePropertySource(String name, String configCoreUrl)
	{
		super(name, JAXRSClientFactory.create(configCoreUrl, ConfigWebService.class, getJaxrsProviders()));
	}

	private static List<Object> getJaxrsProviders()
	{
		return Arrays.asList(new ConfigRequestLogger(), new RestExceptionMapper(),
				new RestExceptionBodyReader(), new RestExceptionCxfClientMapper(), new JacksonJsonProvider());
	}

	@Override
	public Object getProperty(String name)
	{
		try
		{
			return source.getConfigValue(name);
		}
		catch(NotFoundRestException exception)
		{
			return null;
		}
		catch(RuntimeException exception)
		{
			logger.error("Error reading property name \"" + name + "\" from config-core.", exception);
			return null;
		}
	}
}
