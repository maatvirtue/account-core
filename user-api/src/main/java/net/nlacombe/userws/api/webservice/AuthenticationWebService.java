package net.nlacombe.userws.api.webservice;

import net.nlacombe.userws.api.dto.JwsToken;
import net.nlacombe.userws.api.dto.PasswordCredential;
import net.nlacombe.wsutils.restexception.exception.NotFoundRestException;

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

	@POST
	@Path("jwt")
	JwsToken authenticateWithExternalJwt(JwsToken jwsToken);
}
