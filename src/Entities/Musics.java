package Entities;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
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
	
	@ManyToMany
	@JoinTable(name="content",joinColumns=@JoinColumn(name="music_id"),inverseJoinColumns=@JoinColumn(name="playlist_id"))
	Set<Playlist> playlist;
	
	@ManyToMany
	@JoinTable(name="likes",joinColumns=@JoinColumn(name="music_id"),inverseJoinColumns=@JoinColumn(name="username"))
	Set<Users> user;
	
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

	public Set<Playlist> getPlaylist() {
		return playlist;
	}

	public void setPlaylist(Set<Playlist> playlist) {
		this.playlist = playlist;
	}

	public Set<Users> getUser() {
		return user;
	}

	public void setUser(Set<Users> user) {
		this.user = user;
	}
	
	
}
