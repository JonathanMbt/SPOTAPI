package tests;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import dao.DaoMysql;
import dao.IDao;
import entities.Artists;
import entities.Musics;
import entities.Users;

public class Tests_DAO_Musics 
{

	private IDao dao;
	
	private Artists artistA;
	private Artists artistB;
	private Musics musicA;
	private Users userA;

	@Before
	public void setUp() throws Exception 
	{
		dao = new DaoMysql();
		
		artistA = new Artists("NF", "Capitol Records", "Description1");
		artistB = new Artists("Bastille", "EMI", "Description2");
		
		dao.getDaoArtists().create(artistA);
		dao.getDaoArtists().create(artistB);
		
		musicA = new Musics("The Search", "Rap", artistA); 
		Musics musicB = new Musics("Time", "Rap", artistA); 
		Musics musicC = new Musics("Just Like You", "Rap", artistA);
		Musics musicD = new Musics("Pompeii", "Pop", artistB);
		
		userA = new Users("Niels", "npetersen@gmail.com", "test");
		Users userB = new Users("Jonathan", "mbtjonathan@gmail.com", "test");
		
		dao.getDaoUsers().create(userA);
		dao.getDaoUsers().create(userB);
		
		musicA.addUsersLikes(Arrays.asList(userA, userB));
		
		dao.getDaoMusics().create(musicA);
		dao.getDaoMusics().create(musicB);
		dao.getDaoMusics().create(musicC);
		dao.getDaoMusics().create(musicD);
	}

	@After
	public void tearDown() throws Exception 
	{
		// musics will be also deleted as on delete is set to cascade in db
		dao.getDaoArtists().delete("NF"); 
		dao.getDaoArtists().delete("Bastille");
		
		dao.getDaoUsers().delete("Niels");
		dao.getDaoUsers().delete("Jonathan");
	}

	@Test
	public void testGetByTitleExact() 
	{
		List<Musics> musics = dao.getDaoMusics().getByTitle("Pompeii");
		
		assertEquals(1, musics.size());
		assertEquals("Pompeii", musics.get(0).getTitle());
	}
	
	@Test
	public void testGetByTitleSearch() 
	{
		List<String> expectedNames = Arrays.asList("The Search", "Time", "Just Like You");
		List<Musics> musics = dao.getDaoMusics().getByTitle("T"); //not case sensitive
		
		assertEquals(3, musics.size()); 
		
		for(Musics m : musics)
		{
			assertTrue(expectedNames.contains(m.getTitle()));
		}
	}
	
	@Test
	public void testGetByGenre() 
	{
		List<Musics> musics = dao.getDaoMusics().getByGenre("Pop");
		
		assertEquals(1, musics.size());
		assertEquals("Pop", musics.get(0).getGenre());
	}
	
	@Test
	public void testGetByArtist()
	{
		List<Musics> musics = dao.getDaoMusics().getByArtist(artistA);
		
		assertEquals(3, musics.size()); 
		
		for(Musics m : musics)
		{
			assertEquals("NF", m.getArtist().getName());
		}
	}
	
	@Test
	public void testGetById()
	{
		Artists ar = new Artists("Test", "Test", "Test");
		dao.getDaoArtists().create(ar);
		
		Musics m = new Musics("TestM", "TestM", ar);
		dao.getDaoMusics().create(m);

		int id = m.getId();
		
		Musics result = dao.getDaoMusics().getById(id);
		
		assertEquals(id, result.getId());
		
		dao.getDaoArtists().delete("Test");
	}
	
	@Test
	public void testCreate()
	{
		Musics m = new Musics("Cloud", "Rap", artistA);
		  
		dao.getDaoMusics().create(m);
		  
		List<Musics> musics = dao.getDaoMusics().getByGenre("Rap"); 
		assertEquals(4, musics.size());
		  
		dao.getDaoMusics().delete(m.getId());
	}
	
	@Test
	public void testUpdate()
	{
		musicA.setGenre("Pop");
		dao.getDaoMusics().update(musicA);
		
		List<Musics> musicsRap = dao.getDaoMusics().getByGenre("Rap");
		List<Musics> musicsPop = dao.getDaoMusics().getByGenre("Pop");
		
		assertEquals(2, musicsRap.size());
		assertEquals(2, musicsPop.size());
	}
	
	
	@Test
	public void testDelete()
	{
		Artists ar = new Artists("Test", "Test", "Test");
		dao.getDaoArtists().create(ar);
		
		Musics m = new Musics("TestM", "TestM", ar);
		dao.getDaoMusics().create(m);
		
		boolean r = dao.getDaoMusics().delete(m.getId());
		
		List<Musics> musics = dao.getDaoMusics().getByGenre("TestM");
		assertEquals(0, musics.size());
		assertEquals(true, r);
		
		boolean r2 = dao.getDaoMusics().delete(m.getId());
		assertEquals(false, r2);
		
		dao.getDaoArtists().delete("Test");
	}
	
	@Test
	public void testGetNumberOfLikes()
	{
		int rs = dao.getDaoMusics().getNumberOfLikes(musicA.getId());
		assertEquals(2, rs);
		
		int rs2 = dao.getDaoMusics().getNumberOfLikes(-5);
		assertEquals(-1, rs2);
	}
	
	@Test
	public void testGetLikedByUser()
	{
		List<Musics> rs = dao.getDaoMusics().getLikedByUser(userA);
		assertEquals(1, rs.size());
	}

}
