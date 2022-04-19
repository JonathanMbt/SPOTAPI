package dao;

import java.util.List;

import entities.Artists;

public interface IDaoArtists
{
	public List<Artists> getAll();
	public Artists getByExactName(String name);
	public List<Artists> getByName(String name);
	public List<Artists> getByLabel(String label);
	
	public Artists create(Artists artist);
	public Artists update(Artists artist);
	public boolean delete(String name);
}
