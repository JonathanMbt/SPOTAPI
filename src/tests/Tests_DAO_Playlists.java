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
import entities.Playlists;

public class Tests_DAO_Playlists 
{
	private IDao dao;
	private Playlists playlist;
	private Playlists p2;

	@Before
	public void setUp() throws Exception 
	{
		dao = new DaoMysql();
		
		Artists artistA = new Artists("NF", "Capitol Records", "Description1");
		Artists artistB = new Artists("Bastille", "EMI", "Description2");
		
		dao.getDaoArtists().create(artistA);
		dao.getDaoArtists().create(artistB);
		
		playlist = new Playlists("Not Quite Classical", "Description");
		p2 = new Playlists("Clouds", "Test");
		
		Musics musicA = new Musics("The Search", "Rap", artistA); 
		Musics musicB = new Musics("Time", "Rap", artistA); 
		Musics musicC = new Musics("Just Like You", "Rap", artistA);
		Musics musicD = new Musics("Pompeii", "Pop", artistB);
		
		dao.getDaoMusics().create(musicA);
		dao.getDaoMusics().create(musicB);
		dao.getDaoMusics().create(musicC);
		dao.getDaoMusics().create(musicD);
		
		playlist.addMusics(Arrays.asList(musicA, musicB, musicC, musicD));
		
		dao.getDaoPlaylists().create(playlist);
		dao.getDaoPlaylists().create(p2);
	}

	@After
	public void tearDown() throws Exception 
	{
		dao.getDaoArtists().delete("NF");
		dao.getDaoArtists().delete("Bastille");
		
		dao.getDaoPlaylists().delete(playlist.getId());
		dao.getDaoPlaylists().delete(p2.getId());
	}

	@Test
	public void testGetById() 
	{
		Playlists p = new Playlists("Give Me The Future", "Description");
		
		dao.getDaoPlaylists().create(p);

		int id = p.getId();
		
		Playlists result = dao.getDaoPlaylists().getById(id);
		
		assertEquals(id, result.getId());
		
		dao.getDaoPlaylists().delete(id);
	}

	
	@Test
	public void testGetByName()
	{
		List<Playlists> p = dao.getDaoPlaylists().getByName("o"); 
		
		assertEquals(2, p.size()); 
		
	}
	
	@Test
	public void testGetMusics()
	{
		List<Musics> rs = dao.getDaoPlaylists().getMusics(playlist.getId());
		assertEquals(4, rs.size());
	}
	
	@Test
	public void testCreate()
	{
		Playlists p = new Playlists("Give Me The Future", "Description");
		  
		dao.getDaoPlaylists().create(p);
		  
		Playlists rs = dao.getDaoPlaylists().getById(p.getId()); 
		assertNotNull(rs);
		  
		dao.getDaoPlaylists().delete(p.getId());
	}
	
	@Test
	public void testUpdate()
	{
		playlist.setName("Daydreamer");
		dao.getDaoPlaylists().update(playlist);
		
		Playlists rs = dao.getDaoPlaylists().getById(playlist.getId());
		
		assertEquals("Daydreamer", rs.getName());
	}
	
	
	@Test
	public void testDelete()
	{
		Playlists p = new Playlists("Give Me The Future", "Description");
		
		dao.getDaoPlaylists().create(p);
		
		boolean r = dao.getDaoPlaylists().delete(p.getId());
		
		Playlists rs = dao.getDaoPlaylists().getById(p.getId());
		assertNull(rs);
		assertEquals(true, r);
		
		boolean r2 = dao.getDaoPlaylists().delete(p.getId());
		assertEquals(false, r2);
		
		dao.getDaoPlaylists().delete(p.getId());
	}
}
