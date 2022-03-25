package Entities;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Id;

public class Artists {
	@Id
	private String name;
	private String label;
	private String description;
	
	@OneToMany(mappedBy="artist_name",targetEntity=Artists.class)
	List<Musics> music;
	
	public Artists() {
		super();
	}
}
