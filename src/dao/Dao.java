package dao;

public class Dao 
{
	private IDaoMusics daoMusics;
	private IDaoArtists daoArtists;
	
	public Dao()
	{
		daoMusics = new DaoMusics();
		daoArtists = new DaoArtists();
	}

	public IDaoMusics getDaoMusics() {
		return daoMusics;
	}

	public IDaoArtists getDaoArtists() {
		return daoArtists;
	}
}
