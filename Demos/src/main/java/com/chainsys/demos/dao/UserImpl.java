package com.chainsys.demos.dao;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.chainsys.demos.mapper.UserMapper;
import com.chainsys.demos.model.User;

@Repository
public class UserImpl {
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	public void insertUser(User user) throws ClassNotFoundException, SQLException{
		String save = "insert into user(username, password) values (?,?)";
		Object[] params = {user.getUsername(), user.getPassword()};
		int noOfRows = jdbcTemplate.update(save, params);		
	}
	
	public int update(User user) {
		String sql = "update user set password=? where username=?";
		Object[] params = {user.getPassword(), user.getUsername()};
		int noOfRows = jdbcTemplate.update(sql, params);
		return noOfRows;
		
	}
	
	 public List<User> getAllUsers(){
	        String select="select id,username,password from user";
	        List<User> users=jdbcTemplate.query(select, new UserMapper());
	        return users;
	 }
	 
	 public void deleteUser(User user){
	        String delete="delete from user where id=?";
	        Object[] params= {user.getId()};
	        jdbcTemplate.update(delete,params);
	 }
	
	
	
}
