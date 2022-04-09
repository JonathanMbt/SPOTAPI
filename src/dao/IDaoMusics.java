package dao;

import java.util.List;

import entities.Artists;
import entities.Musics;
import entities.Users;

public interface IDaoMusics 
{
	public List<Musics> getByTitle(String title);
	public List<Musics> getByGenre(String genre);
	public int getNumberOfLikes(int musicId);
	public List<Musics> getByArtist(String artistName);
	public Musics getById(int musicId);
	
	public Musics create(Musics music);
	public Musics update(Musics music);
	public void delete(int musicId);
}
