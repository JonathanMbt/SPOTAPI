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
import entities.Musics;

@Path("/musics")
public class MusicsService 
{
	private static IDao dao = new DaoMysql();
	
	@Path("/findByTitle/{title}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Musics> findMusicsByTitle(@PathParam("title") String title)
	{
		return dao.getDaoMusics().getByTitle(title);
	}
	
	@Path("/findByGenre/{genre}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Musics> findMusicsByGenre(@PathParam("genre") String genre)
	{
		return dao.getDaoMusics().getByGenre(genre);
	}
	
	@Path("/findById/{id}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Musics findMusicsById(@PathParam("id") int id)
	{
		return dao.getDaoMusics().getById(id);
	}
	
	@Path("/findByArtist/{artistName}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Musics> findMusicsByArtist(@PathParam("artistName") String artistName)
	{
		return dao.getDaoMusics().getByArtist(artistName);
	}
	
	@Path("/findNumberLikes/{id}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public int findNumberLikes(@PathParam("id") int id)
	{
		return dao.getDaoMusics().getNumberOfLikes(id);
	}
	
	@Path("/findMusicsLikedByUser/{username}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Musics> findMusicsLikedByUser(@PathParam("username") String username)
	{
		return dao.getDaoMusics().getLikedByUser(username);
	}
	
	@Path("/{artistName}")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Musics addMusics(Musics m, @PathParam("artistName") String artistName)
	{
		m.setArtist(dao.getDaoArtists().getByExactName(artistName));
		return dao.getDaoMusics().create(m);
	}
	
	@Path("/update/")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Musics updateMusics(Musics m)
	{
		//to improve because at the moment all fields need to be specified.
		return dao.getDaoMusics().update(m);
	}
	
	@Path("/delete/{id}")
	@DELETE
	public boolean deleteArtists(@PathParam("id") int id)
	{
		return dao.getDaoMusics().delete(id);
	}
}
