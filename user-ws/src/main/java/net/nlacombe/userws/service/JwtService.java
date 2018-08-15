package net.nlacombe.userws.service;

import net.nlacombe.userws.domain.User;

public interface JwtService
{
	String createJwtToken(User user);
}
