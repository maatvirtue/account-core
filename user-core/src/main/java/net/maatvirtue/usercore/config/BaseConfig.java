package net.maatvirtue.usercore.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

@Configuration
public class BaseConfig
{
	@Bean
	public LocalValidatorFactoryBean validator()
	{
		return new LocalValidatorFactoryBean();
	}
}
