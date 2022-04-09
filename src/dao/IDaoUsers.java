package dao;

import entities.Users;

public interface IDaoUsers {
	public Users getUserByUsername(String username);
}
