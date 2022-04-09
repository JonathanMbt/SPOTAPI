package dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import entities.Artists;



public class DaoArtists implements IDaoArtists
{
	
	private EntityManager em;
	
	public DaoArtists()
	{
		EntityManagerFactory emf;
		emf = Persistence.createEntityManagerFactory("UniteSpoty");
		em = emf.createEntityManager();
	}
	
	@Override
	public List<Artists> getAll()
	{
		Query q = em.createQuery("From Artists", Artists.class);
		
		return q.getResultList();	
	}
	
	@Override
	public List<Artists> getByName(String name)
	{	
		Query q = em.createQuery("From Artists WHERE name LIKE :n", Artists.class);
		q.setParameter("n", "%"+name+"%");
		
		return q.getResultList();
	}
	
	@Override
	public List<Artists> getByLabel(String label)
	{
		Query q = em.createQuery("From Artists WHERE label=:l",Artists.class);
		q.setParameter("l", label);
		
		return q.getResultList();
	}

	@Override
	public Artists create(Artists artist) 
	{
		em.getTransaction().begin();
		em.persist(artist);
		em.getTransaction().commit();
		
		return artist;
	}

	@Override
	public void delete(String name) 
	{
		em.getTransaction().begin();
		em.remove(getByName(name).get(0)); // as it's the exact name and the name is unique we only have one value at index 0.
		em.getTransaction().commit();
	}

	@Override
	public Artists updateLabel(String name, String label) 
	{
		Artists artist = getByName(name).get(0);
		
		em.getTransaction().begin();
		artist.setLabel(label); // Once an entity object is retrieved from the database (no matter which way) it can simply be modified in memory from inside an active transaction
		em.getTransaction().commit();
		
		return artist;
	}

	@Override
	public Artists updateDescription(String name, String description) 
	{
		Artists artist = getByName(name).get(0);
		
		em.getTransaction().begin();
		artist.setDescription(description);
		em.getTransaction().commit();
		
		return artist;
	}
	
}
