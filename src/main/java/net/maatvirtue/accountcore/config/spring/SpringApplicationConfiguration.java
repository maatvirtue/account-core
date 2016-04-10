package net.maatvirtue.accountcore.config.spring;

import net.maatvirtue.accountcore.constants.Constants;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = Constants.WEBSERVICE_PACKAGE)
public class SpringApplicationConfiguration
{
	//
}
