package net.nlacombe.userws.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

@Configuration
@PropertySource("classpath:/secret.properties")
public class BaseConfig
{
	@Bean
	public LocalValidatorFactoryBean validator()
	{
		return new LocalValidatorFactoryBean();
	}
}
