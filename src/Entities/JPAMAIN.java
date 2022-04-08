package Entities;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;



public class jpaMain {

	public static void main(String[] args) {
		EntityManagerFactory emf ;
		emf = Persistence.createEntityManagerFactory("UniteSpoty");
		EntityManager entityManager = emf.createEntityManager();
		Query q = entityManager.createQuery("from Artists" , Artists.class);
		
		List<Artists> artists ;
		artists = q.getResultList();
		for (Artists a : artists) {
			System.out.println(a.getName());
			System.out.println(a.getMusic().get(0).getTitle());
		}
		
		System.out.println("-----");
		
		q = entityManager.createQuery("from Musics" , Musics.class);
		List<Musics> musics ;
		musics = q.getResultList();
		for (Musics m : musics) {
			System.out.println(m.getTitle());
			System.out.println(m.getArtist_name().getLabel());
			for(Playlist p : m.getPlaylist())
			{
				System.out.println(p.getName());
			}
		}
		
		// à tester dans les 2 sens + likes
		
	}

}
