package dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import entities.Artists;
import entities.Musics;
import entities.Playlist;
import entities.Users;

public class DaoPlaylist implements IDaoPlaylist {

	private EntityManager em;
	
	public DaoPlaylist()
	{
		EntityManagerFactory emf;
		emf = Persistence.createEntityManagerFactory("UniteSpoty");
		em = emf.createEntityManager();
	}

	@Override
	public List<Playlist> getByName(String name) {
		Query q = em.createQuery("From Playlist WHERE name LIKE :n", Playlist.class);
		q.setParameter("n", "%"+name+"%");
		
		return q.getResultList();
	}

	@Override
	public List<Musics> getMusics(int id) {
		Query q = em.createQuery("From Playlist WHERE id=:id", Playlist.class);
		q.setParameter("id", id);
		List<Musics> result = new ArrayList<>();
		
		List<Playlist> playlists = q.getResultList();
		Playlist playlist = playlists.get(0);
		
		
		return playlist.getMusics();
	}
	


}
