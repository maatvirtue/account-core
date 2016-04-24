package net.maatvirtue.usercore.api.webservice;

import net.maatvirtue.usercore.api.dto.Email;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/registration")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public interface RegistrationWebService
{
	@POST
	void register(Email email);
}
