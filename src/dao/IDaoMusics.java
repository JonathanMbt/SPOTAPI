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
	public List<Musics> getByArtist(Artists artist);
	public List<Musics> getByArtist(String artistName);
	public Musics getById(int musicId);
	public List<Musics> getLikedByUser(Users user);
	public List<Musics> getLikedByUser(String username);
	public List<String> getGenres();
	public List<Musics> getAll();
	
	public Musics create(Musics music);
	public Musics update(Musics music);
	public boolean delete(int musicId);
}
