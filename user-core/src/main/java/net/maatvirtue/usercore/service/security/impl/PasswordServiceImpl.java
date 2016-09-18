package net.maatvirtue.usercore.service.security.impl;

import net.maatvirtue.commonlib.service.crypto.CryptoService;
import net.maatvirtue.commonlib.util.GenericUtil;
import net.maatvirtue.usercore.api.dto.PasswordCredential;
import net.maatvirtue.usercore.constants.Constants;
import net.maatvirtue.usercore.domain.User;
import net.maatvirtue.usercore.domain.security.StoredPasswordCredential;
import net.maatvirtue.usercore.repository.StoredPasswordRepository;
import net.maatvirtue.usercore.service.ConfirmationEmailService;
import net.maatvirtue.usercore.service.security.PasswordService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.Arrays;

@Service
@Transactional
public class PasswordServiceImpl implements PasswordService
{
	private CryptoService cryptoService = CryptoService.getInstance();

	@Inject
	private StoredPasswordRepository storedPasswordRepository;

	@Inject
	private ConfirmationEmailService confirmationEmailService;

	@Override
	public User authenticate(PasswordCredential providedCredential)
	{
		StoredPasswordCredential storedCredential = storedPasswordRepository.findByUsername(providedCredential.getUsername());

		if (storedCredential == null)
			return null;

		String salt = storedCredential.getSalt();
		String providedPassword = providedCredential.getPassword();
		byte[] storedPasswordHash = storedCredential.getPasswordHash();

		if (!passwordMatches(salt, providedPassword, storedPasswordHash))
			return null;

		User user = storedCredential.getUser();

		confirmationEmailService.confirmEmail(user, providedPassword);

		return user;
	}

	private boolean passwordMatches(String salt, String providedPassword, byte[] storedPasswordHash)
	{
		byte[] providedPasswordHash = hashPassword(providedPassword, salt);

		if (providedPasswordHash == null)
			return false;

		return Arrays.equals(providedPasswordHash, storedPasswordHash);
	}

	@Override
	public String createNewPasswordCredential(User user)
	{
		String password = generateRandomPassword();
		PasswordCredential passwordCredential = new PasswordCredential(user.getUsername(), password);

		createPasswordCredential(user, passwordCredential);

		return password;
	}

	@Override
	public String updateUserWithNewRandomPassword(User user)
	{
		StoredPasswordCredential storedPassword = storedPasswordRepository.findByUsername(user.getUsername());

		if (storedPassword == null)
			throw new IllegalStateException("User with username \"" + user.getUsername() + "\" does not have a password credential.");

		String newPassword = generateRandomPassword();

		applyNewPassword(storedPassword, newPassword);

		storedPasswordRepository.save(storedPassword);

		return newPassword;
	}

	private void createPasswordCredential(User user, PasswordCredential passwordCredential)
	{
		if (storedPasswordRepository.findByUsername(user.getUsername()) != null)
			throw new IllegalStateException("User with username \"" + user.getUsername() + "\" already has a password credential.");

		StoredPasswordCredential storedPassword = new StoredPasswordCredential();
		storedPassword.setUser(user);
		applyNewPassword(storedPassword, passwordCredential.getPassword());

		storedPasswordRepository.save(storedPassword);
	}

	private void applyNewPassword(StoredPasswordCredential storedPassword, String newPassword)
	{
		String newSalt = generateNewSalt();
		byte[] newPasswordHash = hashPassword(newPassword, newSalt);

		storedPassword.setSalt(newSalt);
		storedPassword.setPasswordHash(newPasswordHash);
	}

	private String generateRandomPassword()
	{
		return GenericUtil.generateRandomString(Constants.DEFAULT_PASSWORD_LEN);
	}

	private String generateNewSalt()
	{
		return GenericUtil.generateRandomString(Constants.SALT_LEN);
	}

	private byte[] hashPassword(String password, String salt)
	{
		return cryptoService.sha256(salt + password);
	}
}
