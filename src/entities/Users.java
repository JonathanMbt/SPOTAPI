package entities;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Id;


@Entity @Table(name ="users")
public class Users implements Serializable
{

	private static final long serialVersionUID = 1L;
	
	@Id
	private String username;
	private String mail;
	private String password;
	
	@ManyToMany(mappedBy="users",targetEntity=Musics.class)
	Set<Musics> likedMusics;
	
	public Users(){}
	
	public Users(String username, String mail, String password)
	{
		this.username = username;
		this.mail = mail;
		this.password = password;
	}
	
	public String getUsername() 
	{
		return username;
	}
	
	public void setUsername(String username) 
	{
		this.username = username;
	}
	
	public String getMail() 
	{
		return mail;
	}
	
	public void setMail(String mail) 
	{
		this.mail = mail;
	}
	
	public String getPassword() 
	{
		return password;
	}
	
	public void setPassword(String password) 
	{
		this.password = password;
	}

	public Set<Musics> getLikedMusics() 
	{
		return likedMusics;
	}

	public void setLikedMusics(Set<Musics> likedMusics) 
	{
		this.likedMusics = likedMusics;
	}
	
	
}
