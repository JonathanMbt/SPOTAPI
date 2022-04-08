package Dao;

import java.util.List;

import Entities.Musics;
import Entities.Playlist;

public interface IDaoPlaylist 
{
	public List<Playlist> getByName(String name);
	public List<Musics> getMusics(int id);
}
