package tests;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import dao.Dao;
import entities.Artists;
import entities.Musics;

public class Tests_DAO_Musics {

	private Dao dao;
	
	private Artists artistA;
	private Artists artistB;
	private Musics musicA;

	@Before
	public void setUp() throws Exception 
	{
		dao = new Dao();
		artistA = new Artists("NF", "Capitol Records", "Description1");
		artistB = new Artists("Bastille", "EMI", "Description2");
		
		musicA = new Musics("The Search", "Rap", artistA); // as cascade type persist is on we don't need to persist artist first, it will be done automatically
		Musics musicB = new Musics("Time", "Rap", artistA); 
		Musics musicC = new Musics("Just Like You", "Rap", artistA);
		Musics musicD = new Musics("Pompeii", "Pop", artistB); 
		
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
		List<Musics> musics = dao.getDaoMusics().getByArtist("NF");
		
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
		
		musicA.setGenre("Rap");
		dao.getDaoMusics().update(musicA);
	}
	
	
	@Test
	public void testDelete()
	{
		Artists ar = new Artists("Test", "Test", "Test");
		Musics m = new Musics("TestM", "TestM", ar);
		dao.getDaoMusics().create(m);
		
		dao.getDaoMusics().delete(m.getId());
		
		List<Musics> musics = dao.getDaoMusics().getByGenre("TestM");
		assertEquals(0, musics.size());
		
		dao.getDaoArtists().delete("Test");
	}

}
