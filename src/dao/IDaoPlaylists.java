package dao;

import java.util.List;

import entities.Musics;
import entities.Playlists;

public interface IDaoPlaylists 
{
	public Playlists getById(int id);
	public List<Playlists> getByName(String name);
	public List<Musics> getMusics(int id);
	public List<Playlists> getAll();
	
	public Playlists create(Playlists playlist);
	public Playlists update(Playlists playlist);
	public boolean delete(int id);
}
