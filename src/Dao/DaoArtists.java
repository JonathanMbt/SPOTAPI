package Dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import Entities.Artists;



public class DaoArtists implements IDaoArtists{
	
	private EntityManager em;
	
	public DaoArtists(){
		
		EntityManagerFactory emf;
		emf = Persistence.createEntityManagerFactory("UniteSpoty");
		em = emf.createEntityManager();
	}
	
	public List<Artists> getAll(){
		
		Query q = em.createQuery("From Artists", Artists.class);
		return q.getResultList();
		
	}
	
	public Artists getByName(String name){
		
		Query q = em.createQuery("From Artists WHERE name LIKE :n", Artists.class);
		q.setParameter("n", "%"+name+"%");
		List<Artists> artists=q.getResultList();
		
		return artists.get(0);
		
	}
	public List<Artists> getByLabel(String label){
		
		Query q = em.createQuery("From Artists WHERE label=:l",Artists.class);
		q.setParameter("l", label);
		
		return q.getResultList();
	}
	
}
