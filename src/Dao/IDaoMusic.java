package Dao;

import java.util.List;

import Entities.Artists;
import Entities.Musics;
import Entities.Users;

public interface IDaoMusic {

	public List<Musics> getMusicByTitle(String title);
	public List<Musics> getMusicByGenre(String genre);
	public List<Musics> getMusicByLike(Users user);
	public List<Musics> getMusicByArtist(Artists artist);
	
}
