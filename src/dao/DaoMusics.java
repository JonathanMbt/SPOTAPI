package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import entities.Artists;
import entities.Musics;

public class DaoMusics implements IDaoMusics
{
	
	private EntityManager em;
	
	public DaoMusics()
	{
		EntityManagerFactory emf;
		emf = Persistence.createEntityManagerFactory("UniteSpoty");
		em = emf.createEntityManager();
	}
	
	@Override
	public List<Musics> getByGenre(String genre)
	{
		Query q = em.createQuery("From Musics WHERE genre=:g", Musics.class);
		q.setParameter("g", genre);
		
		return q.getResultList();
	}
	
	@Override
	public int getNumberOfLikes(int musicId)
	{
		Query q = em.createQuery("From Musics WHERE id=:id", Musics.class);
		q.setParameter("id", musicId);
		Musics music = (Musics) q.getResultList().get(0);
		
		
		return music.getUsersLikes().size();
	}
	
	@Override
	public List<Musics> getByArtist(String artistName)
	{
		Query q = em.createQuery("Select m From Musics m INNER JOIN Artists a ON m.artist = a.name WHERE a.name=:artistn", Musics.class);
		q.setParameter("artistn", artistName);
		
		return q.getResultList();
	}

	@Override
	public List<Musics> getByTitle(String title)
	{
		Query q = em.createQuery("From Musics WHERE title LIKE :t", Musics.class);
		q.setParameter("t", "%"+title+"%");
		
		return q.getResultList();
	}

	@Override
	public Musics create(Musics music) 
	{
		em.getTransaction().begin();
		em.persist(music);
		em.getTransaction().commit();
		
		return music;
	}

	@Override
	public Musics update(Musics music) 
	{
		em.getTransaction().begin();
		em.merge(music);
		em.getTransaction().commit();
		
		return music;
	}

	@Override
	public void delete(int musicId)
	{
		em.getTransaction().begin();
		em.remove(getById(musicId));
		em.getTransaction().commit();
	}

	@Override
	public Musics getById(int musicId) 
	{
		Query q = em.createQuery("From Musics WHERE id=:id", Musics.class);
		q.setParameter("id", musicId);
		
		return (Musics) q.getResultList().get(0);
	}
}
