package tests;

import static org.junit.Assert.*;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import dao.DaoMysql;
import dao.IDao;
import entities.Users;

public class Tests_DAO_Users 
{
	
	private IDao dao;
	private Users userA;

	@Before
	public void setUp() throws Exception 
	{
		dao = new DaoMysql();
		userA = new Users("Jonathan", "mbtjonathan@gmail.com", "test");
		dao.getDaoUsers().create(userA);
	}

	@After
	public void tearDown() throws Exception 
	{
		dao.getDaoUsers().delete("Jonathan");
	}

	@Test
	public void testGetByUsername() 
	{
		Users user = dao.getDaoUsers().getByUsername("Jonathan");
		
		assertEquals("Jonathan", user.getUsername());
	}
	
	@Test
	public void testIsPasswordCorrect()
	{
		assertEquals(false, dao.getDaoUsers().isPasswordCorrect("Jonathan", "t"));
		assertEquals(true, dao.getDaoUsers().isPasswordCorrect("Jonathan", "test"));
		assertEquals(false, dao.getDaoUsers().isPasswordCorrect("Jhonatan", "test"));
	}
	
	@Test
	public void testCreate()
	{
		Users u = new Users("Niels", "npetersen@gmail.com", "test");
		dao.getDaoUsers().create(u);
		
		Users result = dao.getDaoUsers().getByUsername(u.getUsername());
		assertNotNull(result);
		
		dao.getDaoUsers().delete("Niels");
	}
	
	@Test
	public void testUpdate()
	{
		userA.setMail("test@gmail.com");
		dao.getDaoUsers().update(userA);
		
		Users result = dao.getDaoUsers().getByUsername(userA.getUsername());
		
		assertEquals("test@gmail.com", result.getMail());
	}
	
	@Test
	public void testDelete()
	{
		Users u = new Users("Niels", "npetersen@gmail.com", "test");
		dao.getDaoUsers().create(u);
		
		boolean r = dao.getDaoUsers().delete(u.getUsername());
		
		Users result = dao.getDaoUsers().getByUsername(u.getUsername());
		assertNull(result);
		assertEquals(true, r);
		
		boolean r2 = dao.getDaoUsers().delete("t");
		assertEquals(false, r2);
	}

}
