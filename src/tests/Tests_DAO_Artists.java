package tests;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import dao.DaoArtists;
import dao.IDaoArtists;
import entities.Artists;

public class Tests_DAO_Artists {
	
	private IDaoArtists daoArtist;

	@Before
	public void setUp() throws Exception 
	{
		daoArtist = new DaoArtists();
		Artists a = new Artists("NF", "Capitol Records", "Description1");
		Artists b = new Artists("Jakob Ahlbom", "Epidemic Sound", "Description2");
		Artists c = new Artists("James Blunt", "Custard/Atlantic", "Description3");
		daoArtist.create(a);
		daoArtist.create(b);
		daoArtist.create(c);
	}

	@After
	public void tearDown() throws Exception 
	{
		daoArtist.delete("NF");
		daoArtist.delete("Jakob Ahlbom");
		daoArtist.delete("James Blunt");
	}

	@Test
	public void testGetAll() 
	{
		List<Artists> artists = daoArtist.getAll();
		
		assertEquals(3, artists.size());
	}
	
	@Test
	public void testGetByNameExact()
	{
		List<Artists> artists = daoArtist.getByName("NF");
		
		assertEquals(1, artists.size());
		assertEquals("NF", artists.get(0).getName());
	}
	
	@Test
	public void testGetByNameSearch()
	{
		List<String> expectedNames = Arrays.asList("Jakob Ahlbom", "James Blunt");
		List<Artists> artists = daoArtist.getByName("Ja");
		
		assertEquals(2, artists.size());
		
		for(Artists a : artists)
		{
			assertTrue(expectedNames.contains(a.getName()));
		}
	}
	
	@Test
	public void testGetByLabel()
	{
		List<Artists> artists = daoArtist.getByLabel("Epidemic Sound");
		
		assertEquals(1, artists.size());
		assertEquals("Epidemic Sound", artists.get(0).getLabel());
	}
	
	@Test
	public void testCreate()
	{
		Artists artist = new Artists("Paulo Londra", "WEA Latina", "Description4");
		daoArtist.create(artist);
		
		List<Artists> artists = daoArtist.getAll();
		assertEquals(4, artists.size());
		
		daoArtist.delete(artist.getName());
	}
	
	@Test
	public void testUpdateLabel()
	{
		List<String> expectedLabels = Arrays.asList("TEST", "Epidemic Sound", "Custard/Atlantic");
		
		daoArtist.updateLabel("NF", "TEST");
		List<Artists> artists = daoArtist.getAll();
		
		for(Artists a : artists)
		{
			assertTrue(expectedLabels.contains(a.getLabel()));
		}
		
	}

	
	@Test
	public void testUpdateDescription()
	{
		List<String> expectedDescription = Arrays.asList("TEST", "Description1", "Description2");
		
		daoArtist.updateDescription("James Blunt", "TEST");
		List<Artists> artists = daoArtist.getAll();
		
		for(Artists a : artists)
		{
			assertTrue(expectedDescription.contains(a.getDescription()));
		}
		
	}
	
	@Test
	public void testDelete()
	{
		Artists artist = new Artists("Paulo Londra", "WEA Latina", "Description4");
		daoArtist.create(artist); // already test
		
		daoArtist.delete(artist.getName());
		
		List<Artists> artists = daoArtist.getAll();
		assertEquals(3, artists.size());
	}
}
