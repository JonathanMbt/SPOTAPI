package dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

import entities.Artists;
import entities.Musics;
import entities.Users;

public class DaoMusics implements IDaoMusics
{
	
	private EntityManager em;
	
	public DaoMusics(EntityManagerFactory emf)
	{
		this.em = emf.createEntityManager();
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
		Musics music = getById(musicId);
		
		if (music != null)
		{
			return music.getUsersLikes().size();			
		}
		
		return -1;
	}
	
	@Override
	public List<Musics> getByArtist(Artists artist)
	{
		artist = em.find(Artists.class, artist.getName()); //to refresh the instance of artist and be sure to have all musics.
		
		List<Musics> result = new ArrayList<Musics>();
		result.addAll(artist.getMusic());
		
		return result;
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
	public boolean delete(int musicId)
	{
		Musics rs = getById(musicId);
		
		if(rs != null)
		{
			em.getTransaction().begin();
			em.remove(rs);
			em.getTransaction().commit();
		}
		
		return rs != null;
	}

	@Override
	public Musics getById(int musicId) 
	{
		try 
		{
			Query q = em.createQuery("From Musics WHERE id=:id", Musics.class);
			q.setParameter("id", musicId);
			Musics rs = (Musics) q.getResultList().get(0);
			
			return rs;
		}catch (IndexOutOfBoundsException e) 
		{
			return null;
		}

	}

	@Override
	public List<Musics> getLikedByUser(Users user) 
	{
		user = em.find(Users.class, user.getUsername());
		
		List<Musics> result = new ArrayList<Musics>();
		result.addAll(user.getLikedMusics());
		
		return result;
	}
}
