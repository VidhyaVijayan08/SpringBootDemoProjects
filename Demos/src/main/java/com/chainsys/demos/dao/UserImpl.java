package com.chainsys.demos.dao;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.chainsys.demos.mapper.UserMapper;
import com.chainsys.demos.model.User;

@Repository
public class UserImpl implements UserDAO {
	@Autowired
	JdbcTemplate jdbcTemplate;
	UserMapper mapper;
	@Override
	public void insertUser(User user) {
		String save = "insert into user(username, password,status) values (?,?,?)";
		Object[] params = {user.getUsername(), user.getPassword(),1};
		int noOfRows = jdbcTemplate.update(save, params);		
	}
	@Override
	public int update(User user) {
		String sql = "update user set password=? where username=?";
		Object[] params = {user.getPassword(), user.getUsername()};
		int noOfRows = jdbcTemplate.update(sql, params);
		return noOfRows;
		
	}
	@Override
	 public List<User> getAllUsers(){
	        String select="select id,username,password from user where status=1";
	        List<User> users=jdbcTemplate.query(select, new UserMapper());
	        return users;
	 }
//		 public void deleteUser(User user){ String
//		  delete="delete from user where id=?"; Object[] params= {user.getId()};
//		  jdbcTemplate.update(delete,params); }
	 @Override
	 public List<User> search(User user)
	    {
	        String search="select id,username,password from user where username=?";
	        Object[] name = {user.getUsername()};
	    List<User> userList=jdbcTemplate.query(search,new UserMapper(),name);
	        return userList;
	        
	    }

	
	@Override
	 public String findById(Integer userId) {
		 String sql = "select username from user where id=?";
		 System.out.println("user Id passed:" + userId);
		 String queryForObject = null;
		 try {
			 queryForObject = jdbcTemplate.queryForObject(sql, String.class, userId);
			 System.out.println(queryForObject);
		 }catch (EmptyResultDataAccessException e) {
		}
		 return queryForObject;
	 }
	 
	@Override
	public void deleteUser(User user) 
	{
	   String update="update user set status=? where id=?";
	   Object[] params= {user.getStatus(),user.getId()};
	   jdbcTemplate.update(update,params);
	}
	@Override
	public void save(User saveUser) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public List<User> listUsers() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public User findOne(Integer userId) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
