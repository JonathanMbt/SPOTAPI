package Entities;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Id;


@Entity @Table(name ="users")
public class Users {

	@Id
	private String username;
	private String mail;
	private String password;
	
	@ManyToMany(mappedBy="users",targetEntity=Musics.class)
	Set<Musics> musics;
	
	public Users(){
		super();
	}
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
	
}
