package spotPack;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import entities.Artists;

@Path("/artists")
public class ArtistService {

	@Path("/")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Artists[] allEtudiants()
	{
		Artists[] artists=null;
		return artists;
		
	}
	
	
	
}
