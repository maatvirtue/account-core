package net.maatvirtue.usercore.domain.security;

/**
 * Credential that have a maximum lifespan and that are only used temporarily to
 * get access to the website API. TemporaryCredential are disposed at their
 * end of life and renewed using PermanentCredential.
 * 
 * Ex: security tokens.
 */
public interface TemporaryCredential extends Credential
{
	//
}
