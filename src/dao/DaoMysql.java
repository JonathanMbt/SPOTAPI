package dao;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class DaoMysql implements IDao
{
	private IDaoMusics daoMusics;
	private IDaoArtists daoArtists;
	private IDaoUsers daoUsers;
	private IDaoPlaylists daoPlaylists;
	
	public DaoMysql()
	{
		EntityManagerFactory emf;
		emf = Persistence.createEntityManagerFactory("UniteSpoty");
		
		daoMusics = new DaoMusics(emf);
		daoArtists = new DaoArtists(emf);
		daoUsers = new DaoUsers(emf);
		daoPlaylists = new DaoPlaylists(emf);
	}

	public IDaoMusics getDaoMusics() 
	{
		return daoMusics;
	}

	public IDaoArtists getDaoArtists() 
	{
		return daoArtists;
	}

	public IDaoUsers getDaoUsers() 
	{
		return daoUsers;
	}
	
	public IDaoPlaylists getDaoPlaylists()
	{
		return daoPlaylists;
	}
	
}
