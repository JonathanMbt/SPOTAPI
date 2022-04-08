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
		
		Query q = em.createQuery("From Musics",Artists.class);
		List<Musics> musics=q.getResultList();
		ArrayList<Musics> result=new ArrayList<>();
		
		for(Musics m: musics){
			if(m.getGenre()==genre){
				result.add(m);
			}
		}
		return result;
		
	}
	
	public List<Musics> getMusicByLike(Users user){
		
		Query q = em.createQuery("From Musics",Artists.class);
		List<Musics> musics=q.getResultList();
		ArrayList<Musics> result=new ArrayList<>();
		
		for(Musics m: musics){
			if(m.getUser() == user){
				result.add(m);
			}
		}
		return result;
	}
	
	public List<Musics> getMusicByArtist(Artists artist){
		Query q = em.createQuery("From Musics",Artists.class);
		List<Musics> musics=q.getResultList();
		ArrayList<Musics> result=new ArrayList<>();
		
		for(Musics m: musics){
			if(m.getArtist_name() == artist){
				result.add(m);
			}
		}
		return result;
	}
}
