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

public class Tests_DAO_Artists 
{
	
	private IDao dao;
	private Artists artistA;

	@Before
	public void setUp() throws Exception 
	{
		dao = new DaoMysql();
		
		artistA = new Artists("NF", "Capitol Records", "Description1");
		Artists artistB = new Artists("Jakob Ahlbom", "Epidemic Sound", "Description2");
		Artists artistC = new Artists("James Blunt", "Custard/Atlantic", "Description3");
		
		dao.getDaoArtists().create(artistA);
		dao.getDaoArtists().create(artistB);
		dao.getDaoArtists().create(artistC);
	}

	@After
	public void tearDown() throws Exception 
	{
		dao.getDaoArtists().delete("NF");
		dao.getDaoArtists().delete("Jakob Ahlbom");
		dao.getDaoArtists().delete("James Blunt");
	}

	@Test
	public void testGetAll() 
	{
		List<Artists> artists = dao.getDaoArtists().getAll();
		
		assertEquals(3, artists.size());
	}
	
	@Test
	public void testGetByNameExact()
	{
		List<Artists> artists = dao.getDaoArtists().getByName("NF");
		
		assertEquals(1, artists.size());
		assertEquals("NF", artists.get(0).getName());
	}
	
	@Test
	public void testGetByNameSearch()
	{
		List<String> expectedNames = Arrays.asList("Jakob Ahlbom", "James Blunt");
		List<Artists> artists = dao.getDaoArtists().getByName("Ja");
		
		assertEquals(2, artists.size());
		
		for(Artists a : artists)
		{
			assertTrue(expectedNames.contains(a.getName()));
		}
	}
	
	@Test
	public void testGetByLabel()
	{
		List<Artists> artists = dao.getDaoArtists().getByLabel("Epidemic Sound");
		
		assertEquals(1, artists.size());
		assertEquals("Epidemic Sound", artists.get(0).getLabel());
	}
	
	@Test
	public void testCreate()
	{
		Artists artist = new Artists("Paulo Londra", "WEA Latina", "Description4");
		dao.getDaoArtists().create(artist);
		
		List<Artists> artists = dao.getDaoArtists().getAll();
		assertEquals(4, artists.size());
		
		dao.getDaoArtists().delete(artist.getName());
	}
	
	@Test
	public void testUpdate()
	{
		List<String> expectedLabels = Arrays.asList("TEST", "Epidemic Sound", "Custard/Atlantic");
		artistA.setLabel("TEST");
		
		dao.getDaoArtists().update(artistA);
		List<Artists> artists = dao.getDaoArtists().getAll();
		
		for(Artists a : artists)
		{
			assertTrue(expectedLabels.contains(a.getLabel()));
		}
		
	}
	
	@Test
	public void testDelete()
	{
		Artists artist = new Artists("Paulo Londra", "WEA Latina", "Description4");
		dao.getDaoArtists().create(artist); // already test
		
		boolean r = dao.getDaoArtists().delete(artist.getName());

		List<Artists> artists = dao.getDaoArtists().getAll();
		assertEquals(3, artists.size());
		assertEquals(true, r);
		
		boolean r2 = dao.getDaoArtists().delete("Paulo Londra");
		assertEquals(false, r2);
	}
}
