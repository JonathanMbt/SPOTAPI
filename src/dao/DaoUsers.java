package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import entities.Artists;
import entities.Users;

public class DaoUsers implements IDaoUsers {
	
	private EntityManager em;
	
	public DaoUsers(){
		
		EntityManagerFactory emf;
		emf = Persistence.createEntityManagerFactory("UniteSpoty");
		em = emf.createEntityManager();
	}

	@Override
	public Users getUserByUsername(String username) {
		Query q = em.createQuery("FROM Users WHERE username=:username", Users.class);
		q.setParameter("username", username);
	
		List<Users> user = q.getResultList();
		
		return user.get(0);
	}

}
