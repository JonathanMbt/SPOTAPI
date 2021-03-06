package entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.json.bind.annotation.JsonbTransient;
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

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String title;
	private String genre;
	
	@ManyToOne
	@JoinColumn(name="artist")
	private Artists artist;
	
	@ManyToMany(mappedBy="musics",targetEntity=Playlists.class)
	@JsonbTransient
	Set<Playlists> playlist;
	
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
	
	public int getId() 
	{
		return id;
	}

	public void setId(int id) 
	{
		this.id = id;
	}

	public String getTitle() 
	{
		return title;
	}

	public void setTitle(String title) 
	{
		this.title = title;
	}

	public String getGenre() 
	{
		return genre;
	}

	public void setGenre(String genre) 
	{
		this.genre = genre;
	}

	public Artists getArtist() 
	{
		return artist;
	}

	public void setArtist(Artists artist) 
	{
		this.artist = artist;
	}

	public Set<Playlists> getPlaylist() 
	{
		return playlist;
	}

	public void setPlaylist(Set<Playlists> playlist) 
	{
		this.playlist = playlist;
	}
	
	public Set<Users> getUsersLikes()
	{
		return users;
	}
	
	public void setUsersLikes(Set<Users> user)
	{
		this.users=user;
	}
	
	public void addUsersLikes(List<Users> users)
	{
		if(this.users == null)
		{
			this.users = new HashSet<Users>();	
		}
		
		this.users.addAll(users);
	}
	
	
}
