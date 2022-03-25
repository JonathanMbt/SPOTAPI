package Entities;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Id;


@Entity @Table(name ="musics")
public class Musics {
	@Id
	private int id;
	private String title;
	private String genre;
	
	@ManyToOne
	@JoinColumn(name="artist_name")
	private Artists artist_name;
	
	public Musics() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public Artists getArtist_name() {
		return artist_name;
	}

	public void setArtist_name(Artists artist_name) {
		this.artist_name = artist_name;
	}
	
	
}
