package net.maatvirtue.usercore.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

@Configuration
public class BaseConfig
{
	@Bean
	public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer()
	{
		return new PropertySourcesPlaceholderConfigurer();
	}

	@Bean
	public static ConfigCorePropertySource configCorePropertySource(ConfigurableEnvironment configurableEnvironment, @Value("${core.config.url}") String configCoreUrl)
	{
		ConfigCorePropertySource configCorePropertySource = new ConfigCorePropertySource("config-core", configCoreUrl);

		configurableEnvironment.getPropertySources().addLast(configCorePropertySource);

		return configCorePropertySource;
	}

	@Bean
	public LocalValidatorFactoryBean validator()
	{
		return new LocalValidatorFactoryBean();
	}
}
