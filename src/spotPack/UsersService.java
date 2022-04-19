package spotPack;


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
import entities.Users;

@Path("/users")
public class UsersService 
{
	private static IDao dao = new DaoMysql();
	
	@Path("/findByName/{username}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Users findUserByName(@PathParam("username") String username)
	{
		return dao.getDaoUsers().getByUsername(username);
	}
	
	@Path("{username}/validatePassword/{inputPassword}")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public boolean isPasswordCorrect(@PathParam("username") String username, @PathParam("inputPassword") String inputPassword)
	{
		return dao.getDaoUsers().isPasswordCorrect(username, inputPassword);
	}
	
	@Path("/")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Users addUser(Users u)
	{
		return dao.getDaoUsers().create(u);
	}
	
	@Path("/update/")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Users updatePlaylist(Users u)
	{
		return dao.getDaoUsers().update(u);
	}
	
	@Path("/delete/{username}")
	@DELETE
	public boolean deleteArtists(@PathParam("username") String username)
	{
		return dao.getDaoUsers().delete(username);
	}
}
