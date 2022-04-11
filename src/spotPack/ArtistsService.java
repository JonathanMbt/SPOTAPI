package spotPack;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import dao.DaoMysql;
import dao.IDao;
import entities.Artists;

@Path("/artists")
public class ArtistsService 
{
	
	private static IDao dao = new DaoMysql();

	@Path("/all")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Artists> allArtists()
	{
		return dao.getDaoArtists().getAll();
	}
	
	@Path("/")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Artists addArtist(Artists a)
	{
		return dao.getDaoArtists().create(a);
	}
	
	
	@Path("/findByName/{id}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Artists> findArtistByName(@PathParam("id") String id)
	{
		return dao.getDaoArtists().getByName(id);
	}
	
	@Path("/findByLabel/{label}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Artists> findArtistByLabel(@PathParam("label") String label)
	{
		return dao.getDaoArtists().getByLabel(label);
	}
	
	@Path("/update/")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Artists updateArtist(Artists a)
	{
		//to improve because at the moment all fields need to be specified.
		return dao.getDaoArtists().update(a);
	}
	
	@Path("/delete/{id}")
	@DELETE
	public boolean deleteArtists(@PathParam("id") String id)
	{
		return dao.getDaoArtists().delete(id);
	}
}
