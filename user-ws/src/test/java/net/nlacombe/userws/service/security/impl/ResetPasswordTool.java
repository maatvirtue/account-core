package net.nlacombe.userws.service.security.impl;

import net.nlacombe.userws.domain.User;
import net.nlacombe.userws.domain.UserStatus;
import net.nlacombe.userws.service.UserService;
import net.nlacombe.userws.service.security.PasswordService;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.inject.Inject;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Ignore
public class ResetPasswordTool
{
	private static final Logger logger = LoggerFactory.getLogger(ResetPasswordTool.class);

	@Inject
	private PasswordService passwordService;

	@Inject
	private UserService userService;

	@Test
	public void reset_user_password()
	{
		String username = "nicolas.m.lacombe@gmail.com";
		User user = userService.getUserByUsernameAndStatus(username, UserStatus.ACTIVE);

		String password = passwordService.updateUserWithNewRandomPassword(user);

		logger.info("New password for user with username: \"" + username + "\" : \"" + password + "\"");
	}
}
