package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

import entities.Artists;



public class DaoArtists implements IDaoArtists
{
	
	private EntityManager em;
	
	public DaoArtists(EntityManagerFactory emf)
	{
		this.em = emf.createEntityManager();
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
		Query q = em.createQuery("From Artists WHERE label=:l", Artists.class);
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
	public boolean delete(String name) 
	{
		List<Artists> rs = getByName(name);
		
		if(rs.size() == 1)
		{
			em.getTransaction().begin();
			em.remove(rs.get(0)); // as it's the exact name and the name is unique we only have one value at index 0.
			em.getTransaction().commit();
			return true;
		}

		return false;
	}

	@Override
	public Artists update(Artists artist) 
	{
		em.getTransaction().begin();
		em.merge(artist);
		em.getTransaction().commit();
		
		return artist;
	}

	@Override
	public Artists getByExactName(String name) 
	{
		return em.find(Artists.class, name);
	}
}
