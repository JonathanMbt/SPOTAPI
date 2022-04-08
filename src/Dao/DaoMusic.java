package Dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import Entities.Artists;
import Entities.Musics;
import Entities.Users;

public class DaoMusic implements IDaoMusic{
	
	private EntityManager em;
	
	public DaoMusic(){
		
		EntityManagerFactory emf;
		emf = Persistence.createEntityManagerFactory("UniteSpoty");
		em = emf.createEntityManager();
	}
	
	public List<Musics> getMusicByGenre(String genre){
		Query q = em.createQuery("From Musics WHERE genre=:g", Musics.class);
		q.setParameter("g", genre);
		
		return q.getResultList();
	}
	
	public List<Musics> getMusicByLike(Users user){
		
		Query q = em.createQuery("From Musics", Musics.class);
		List<Musics> musics = q.getResultList();
		ArrayList<Musics> result = new ArrayList<>();
		
		for(Musics m: musics){
			if(m.getUser() == user){
				result.add(m);
			}
		}
		return result;
	}
	
	public List<Musics> getMusicByArtist(Artists artist){
		Query q = em.createQuery("Select m From Musics m INNER JOIN Artists a ON m.artist_name = a.name WHERE a.name=:artistn", Musics.class);
		q.setParameter("artistn", artist.getName());
		
		return q.getResultList();
	}

	@Override
	public List<Musics> getMusicByTitle(String title) {
		Query q = em.createQuery("From Musics WHERE title LIKE :t", Musics.class);
		q.setParameter("t", "%"+title+"%");
		
		return q.getResultList();
	}
}
