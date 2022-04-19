package dao;

import entities.Users;

public interface IDaoUsers 
{
	public Users getByUsername(String username);
	public boolean isPasswordCorrect(String username, String inputPassword);
	
	public Users create(Users user);
	public Users update(Users user);
	public boolean delete(String username);
}
