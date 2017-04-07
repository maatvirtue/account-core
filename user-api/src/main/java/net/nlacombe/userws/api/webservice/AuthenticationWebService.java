package net.nlacombe.userws.api.webservice;

import net.maatvirtue.authlib.jwt.JwsToken;
import net.maatvirtue.wsutils.restexception.exception.NotFoundRestException;
import net.nlacombe.userws.api.dto.PasswordCredential;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/authentication")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public interface AuthenticationWebService
{
	@POST
	@Path("usernameAndPassword")
	JwsToken authenticate(PasswordCredential passwordCredential) throws NotFoundRestException;
}
