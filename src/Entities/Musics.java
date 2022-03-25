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
	@JoinColumn(name="name")
	private Artists artist_name;
	
	public Musics() {
		super();
	}
	
	
}
