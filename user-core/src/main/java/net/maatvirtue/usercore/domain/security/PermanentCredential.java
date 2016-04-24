package net.maatvirtue.usercore.domain.security;

/**
 * Credential which is kept by the user and only rarely used to get TemporaryCredential,
 * which are in term used to access the website API.
 * 
 * Ex: PIN, fingerprint, password.
 */
public interface PermanentCredential extends Credential
{
	//
}
