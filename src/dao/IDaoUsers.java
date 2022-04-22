package dao;

import entities.Users;

public interface IDaoUsers 
{
	public Users getByUsername(String username);
	
	public Users create(Users user);
	public Users update(Users user);
	public boolean delete(String username);
}
