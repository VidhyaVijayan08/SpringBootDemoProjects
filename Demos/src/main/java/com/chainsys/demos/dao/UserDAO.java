package com.chainsys.demos.dao;

import java.util.List;
import org.springframework.stereotype.Repository;
import com.chainsys.demos.model.User;

@Repository
public interface UserDAO {
	public  void save(User saveUser);
	
	public int update(User u1);
	
	public List<User> listUsers();
		
	public User findOne(Integer userId);
	
	public String findById(Integer userId);
}
