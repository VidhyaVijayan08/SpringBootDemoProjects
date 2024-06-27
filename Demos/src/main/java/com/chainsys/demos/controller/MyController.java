package com.chainsys.demos.controller;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.chainsys.demos.dao.UserDAO;
import com.chainsys.demos.dao.UserImpl;
import com.chainsys.demos.mapper.UserMapper;
import com.chainsys.demos.model.User;
@Controller
public class MyController {
	
	@Autowired
	UserImpl userImpl;
	UserDAO userDao;
	JdbcTemplate jdbcTemplate;
	UserMapper mapper;
	
	@RequestMapping("/home")
	public String home() {
//		System.out.println("In home Page");
		return "home.jsp";
	}
	
	
	@RequestMapping("/save")
	public String save() {
		return "success.jsp";
	}
	
	@GetMapping("/register")
	public String saveUser(@RequestParam("username") String username, @RequestParam("password") String password)throws ClassNotFoundException, SQLException{
		System.out.println("In register handler");
		User user = new User();
		
		//Validators
		user.setUsername(username);
		user.setPassword(password);
		System.out.println(user.getUsername() + user.getPassword());
		userImpl.insertUser(user);
		return "edit.jsp";
		
	}
	
	@GetMapping("/delete")
    public String deleteUser(@RequestParam("id") Integer id,Model model)
    {
        User user=new User();
        user.setId(id);
//        user.setStatus(0);
        userImpl.deleteUser(user);
        List<User> users=userImpl.getAllUsers();
        model.addAttribute("user",users);
        return "home.jsp";
    }
	
	@GetMapping("/update")
	public String updateUser(@RequestParam("username") String username, @RequestParam("password") String password)throws ClassNotFoundException, SQLException{
		System.out.println("In register handler");
		User user = new User();
		
		//Validators
		user.setUsername(username);

		user.setPassword(password);
		System.out.println( user.getPassword());
		userImpl.update(user);
		return "success.jsp";
		
	}
	
   
	
	//displaying the data from DB
	@GetMapping("/listofusers")
	public String getAllUser(Model model) {
		System.out.println("getting datas");
		List<User> users = userImpl.getAllUsers();
		System.out.println(users);
		model.addAttribute("USER_LIST",users);
		return "listUser.jsp";
	}
		
	//to find the user by id
	@GetMapping("/findUserbyId")
	public String findUserById(@RequestParam("userId") Integer id,Model model) {
		System.out.println("finding");
		String name = userDao.findById(id);
		System.out.println(name);
		
		if(name == null) {
			throw new EmptyResultDataAccessException("User not found", id);
		}else {
			System.out.println("User Found" + name);
			
			model.addAttribute("userFound", name);
		}
		return "success3.jsp";
	}
	
	
}
