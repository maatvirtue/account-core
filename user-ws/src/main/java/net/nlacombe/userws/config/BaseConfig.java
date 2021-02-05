package net.nlacombe.userws.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import net.nlacombe.authlib.jwt.JwtUtil;
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

	@Bean
	public JwtUtil jwtUtil(ObjectMapper jsonConverter)
	{
		return new JwtUtil(jsonConverter);
	}
}
