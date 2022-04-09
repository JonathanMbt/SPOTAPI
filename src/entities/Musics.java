package entities;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Id;




@Entity @Table(name ="musics")
public class Musics implements Serializable 
{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String title;
	private String genre;
	
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name="artist")
	private Artists artist;
	
	@ManyToMany
	@JoinTable(name="content",joinColumns=@JoinColumn(name="music_id"),inverseJoinColumns=@JoinColumn(name="playlist_id"))
	Set<Playlist> playlist;
	
	@ManyToMany
	@JoinTable(name="likes",joinColumns=@JoinColumn(name="music_id"),inverseJoinColumns=@JoinColumn(name="username"))
	Set<Users> users;
	
	public Musics() {}
	
	public Musics(String title, String genre, Artists artist)
	{
		this.title = title;
		this.genre = genre;
		this.artist = artist;
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

	public Artists getArtist() {
		return artist;
	}

	public void setArtist(Artists artist) {
		this.artist = artist;
	}

	public Set<Playlist> getPlaylist() {
		return playlist;
	}

	public void setPlaylist(Set<Playlist> playlist) {
		this.playlist = playlist;
	}
	
	public Set<Users> getUsersLikes(){
		return users;
	}
	
	public void setUsersLikes(Set<Users> user){
		this.users=user;
	}
	
	
	
}
