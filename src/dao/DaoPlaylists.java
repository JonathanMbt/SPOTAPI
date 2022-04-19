package dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

import entities.Musics;
import entities.Playlists;


public class DaoPlaylists implements IDaoPlaylists 
{

	private EntityManager em;
	
	public DaoPlaylists(EntityManagerFactory emf)
	{
		this.em = emf.createEntityManager();
	}

	@Override
	public List<Playlists> getByName(String name) 
	{
		Query q = em.createQuery("From Playlists WHERE name LIKE :n", Playlists.class);
		q.setParameter("n", "%"+name+"%");
		
		return q.getResultList();
	}

	@Override
	public List<Musics> getMusics(int id) 
	{
		List<Musics> result = new ArrayList<Musics>();
		result.addAll(getById(id).getMusics());
		
		return result;
	}

	@Override
	public Playlists getById(int id) 
	{
		return em.find(Playlists.class, id);
	}

	@Override
	public Playlists create(Playlists playlist) 
	{
		em.getTransaction().begin();
		em.persist(playlist);
		em.getTransaction().commit();
		
		return playlist;
	}

	@Override
	public Playlists update(Playlists playlist) 
	{
		em.getTransaction().begin();
		em.merge(playlist);
		em.getTransaction().commit();
		
		return playlist;
	}

	@Override
	public boolean delete(int id) 
	{
		Playlists rs = getById(id);
		
		if(rs != null)
		{
			em.getTransaction().begin();
			em.remove(rs);
			em.getTransaction().commit();
		}
		
		return rs != null;
	}


}
