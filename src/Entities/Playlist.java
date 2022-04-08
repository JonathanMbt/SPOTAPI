package Entities;

import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Id;

@Entity @Table(name ="playlist")
public class Playlist {
	@Id
	private int id;
	private String name;
	private String description;
	
	
	@ManyToMany(mappedBy="playlist",targetEntity=Musics.class)
	List<Musics> musics;
	
	
	public Playlist(){
		super();
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

	public List<Musics> getMusics() {
		return musics;
	}

	public void setMusics(List<Musics> musics) {
		this.musics = musics;
	}
	
	
	
}
