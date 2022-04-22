package spotPack;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
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
import entities.Playlists;

@Path("/playlists")
public class PlaylistsService 
{
	private static IDao dao = new DaoMysql();
	
	@Path("/all/")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Playlists> getAll()
	{
		return dao.getDaoPlaylists().getAll();
	}
	
	@Path("/findById/{id}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Playlists findPlaylistById(@PathParam("id") int id)
	{
		return dao.getDaoPlaylists().getById(id);
	}
	
	@Path("/findByName/{name}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Playlists> findPlaylistByName(@PathParam("name") String name)
	{
		try {
			name = java.net.URLDecoder.decode(name, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return dao.getDaoPlaylists().getByName(name);
	}
	
	@Path("/getMusics/{id}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Musics> findPlaylistByName(@PathParam("id") int id)
	{
		return dao.getDaoPlaylists().getMusics(id);
	}
	
	@Path("/{idPlaylist}/addMusic/{idMusic}")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public Playlists addMusic(@PathParam("idPlaylist") int idPlaylist, @PathParam("idMusic") int idMusic)
	{
		Playlists p = dao.getDaoPlaylists().getById(idPlaylist);
		Musics m = dao.getDaoMusics().getById(idMusic);
		
		List<Musics> lm = new ArrayList<Musics>();
		lm.add(m);
		p.addMusics(lm);
		
		return dao.getDaoPlaylists().update(p);
	}
	
	@Path("/")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Playlists addPlaylist(Playlists p)
	{
		return dao.getDaoPlaylists().create(p);
	}
	
	@Path("/update/")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Playlists updatePlaylist(Playlists p)
	{
		return dao.getDaoPlaylists().update(p);
	}
	
	@Path("/delete/{id}")
	@DELETE
	public boolean deletePlaylist(@PathParam("id") int id)
	{
		return dao.getDaoPlaylists().delete(id);
	}
}
