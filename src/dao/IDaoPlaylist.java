package dao;

import java.util.List;

import entities.Musics;
import entities.Playlist;

public interface IDaoPlaylist 
{
	public List<Playlist> getByName(String name);
	public List<Musics> getMusics(int id);
}
