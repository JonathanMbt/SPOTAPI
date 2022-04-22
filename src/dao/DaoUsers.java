package dao;


import javax.persistence.CacheRetrieveMode;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

import entities.Users;

public class DaoUsers implements IDaoUsers 
{
	
	private EntityManager em;
	
	public DaoUsers(EntityManagerFactory emf)
	{
		this.em = emf.createEntityManager();
	}

	@Override
	public Users getByUsername(String username) 
	{
		try 
		{
			Query q = em.createQuery("FROM Users WHERE username=:username", Users.class);
			q.setParameter("username", username);
		
			Users user = (Users) q.getResultList().get(0);
			
			return user;
		}catch (Exception e) 
		{
			return null;
		}

	}

	@Override
	public Users create(Users user) 
	{
		em.getTransaction().begin();
		em.persist(user);
		em.getTransaction().commit();
		
		return user;
	}

	@Override
	public Users update(Users user) 
	{
		em.getTransaction().begin();
		em.merge(user);
		em.getTransaction().commit();
		
		return user;
	}

	@Override
	public boolean delete(String username) 
	{
		Users u = getByUsername(username);
		
		if(u != null)
		{
			em.getTransaction().begin();
			em.remove(u);
			em.getTransaction().commit();
		}
			
		return u != null;
	}

}
