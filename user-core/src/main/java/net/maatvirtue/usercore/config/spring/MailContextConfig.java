package net.maatvirtue.usercore.config.spring;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

@Configuration
public class MailContextConfig
{
	@Value("${email.host}")
	private String emailHost;

	@Value("${email.port}")
	private int emailPort;

	@Value("${email.username}")
	private String emailUsername;

	@Value("${email.password}")
	private String emailPassword;

	@Bean
	public JavaMailSenderImpl mailSender()
	{
		JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
		mailSender.setHost(emailHost);
		mailSender.setPort(emailPort);
		mailSender.setUsername(emailUsername);
		mailSender.setPassword(emailPassword);

		Properties javaMailProperties = new Properties();
		javaMailProperties.put("mail.transport.protocol", "smtp");
		javaMailProperties.put("mail.smtp.auth", true);
		javaMailProperties.put("mail.smtp.starttls.enable", true);
		javaMailProperties.put("mail.debug", false);

		mailSender.setJavaMailProperties(javaMailProperties);

		return mailSender;
	}
}
