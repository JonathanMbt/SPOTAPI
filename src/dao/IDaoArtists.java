package dao;

import java.util.List;

import entities.Artists;

public interface IDaoArtists
{
	public List<Artists> getAll();
	public List<Artists> getByName(String name);
	public List<Artists> getByLabel(String label);
	
	public Artists create(Artists artist);
	public Artists updateLabel(String name, String label);
	public Artists updateDescription(String name, String description);
	public void delete(String name);
}
