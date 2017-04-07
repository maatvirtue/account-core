package net.nlacombe.userws.mapper;

import net.nlacombe.userws.domain.Email;
import net.nlacombe.userws.entity.EmailEntity;
import org.springframework.stereotype.Component;

import javax.inject.Inject;

@Component
public class EmailEntityMapper extends AbstractEntityMapper<Email, EmailEntity>
{
	@Inject
	private UserEntityMapper userEntityMapper;

	@Override
	public Email mapToDomainType(EmailEntity emailEntity, MapperCache cache)
	{
		if(cache.contains(emailEntity))
			return (Email)cache.get(emailEntity);

		Email email = new Email();

		cache.put(emailEntity, email);

		email.setEmailId(emailEntity.getEmailId());
		email.setEmail(emailEntity.getEmail());
		email.setConfirmed(emailEntity.isConfirmed());
		email.setPrimary(emailEntity.isPrimary());
		email.setUser(userEntityMapper.mapToDomainType(emailEntity.getUser(), cache));

		return email;
	}

	@Override
	public EmailEntity mapToEntityType(Email email, MapperCache cache)
	{
		if(cache.contains(email))
			return (EmailEntity)cache.get(email);

		EmailEntity emailEntity = new EmailEntity();

		cache.put(email, emailEntity);

		emailEntity.setEmailId(email.getEmailId());
		emailEntity.setEmail(email.getEmail());
		emailEntity.setConfirmed(email.isConfirmed());
		emailEntity.setPrimary(email.isPrimary());
		emailEntity.setUser(userEntityMapper.mapToEntityType(email.getUser(), cache));

		return emailEntity;
	}
}
