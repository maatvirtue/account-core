package net.maatvirtue.usercore.config.spring;

import net.maatvirtue.usercore.constants.Constants;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

@Configuration
@PropertySource("classpath:config.properties")
@ComponentScan(basePackages = Constants.BASE_PACKAGE)
public class BaseContextConfig
{
	@Bean
	public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer()
	{
		return new PropertySourcesPlaceholderConfigurer();
	}

	@Bean
	public LocalValidatorFactoryBean validator()
	{
		return new LocalValidatorFactoryBean();
	}
}
