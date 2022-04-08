package Entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Id;

@Entity @Table(name="artists")
public class Artists implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	private String name;
	private String label;
	private String description;
	
	@OneToMany(mappedBy="artist_name", targetEntity=Musics.class)
	List<Musics> music;
	
	public Artists() {
		super();
	}
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<Musics> getMusic() {
		return music;
	}

	public void setMusic(List<Musics> music) {
		this.music = music;
	}


}
