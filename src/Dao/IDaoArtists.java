package Dao;

import java.util.List;

import Entities.Artists;

public interface IDaoArtists {
	
	public List<Artists> getAll();
	public Artists getByName(String name);
	public List<Artists> getByLabel(String label);
	
}
