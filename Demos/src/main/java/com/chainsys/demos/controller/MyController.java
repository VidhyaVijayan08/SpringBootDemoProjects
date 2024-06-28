package com.chainsys.demos.controller;
import java.sql.SQLException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.chainsys.demos.dao.UserDAO;
import com.chainsys.demos.model.User;
@Controller
public class MyController {
	
	@Autowired
	UserDAO userDao;
	
	@RequestMapping("/home")
	public String home() {
		return "home.jsp";
	}
	
	@RequestMapping("/save")
	public String save() {
		return "success.jsp";
	}
	
	@GetMapping("/register")
	public String saveUser(@RequestParam("username") String username, @RequestParam("password") String password)throws ClassNotFoundException, SQLException{
		User user = new User();
		
		//Validators
		user.setUsername(username);
		user.setPassword(password);
		System.out.println(user.getUsername() + user.getPassword());
		userDao.insertUser(user);
		return "redirect:/listofusers";
		
	}
	
	@GetMapping("/delete")
    public String deleteUser(@RequestParam("id") Integer id,Model model)
    {
        User user=new User();
        user.setId(id);
        user.setStatus(0);
        userDao.deleteUser(user);
        List<User> users=userDao.getAllUsers();
        model.addAttribute("user",users);
        return "redirect:/listofusers";
    }
	
	@GetMapping("/update")
	public String updateUser(@RequestParam("username") String username, @RequestParam("password") String password)throws ClassNotFoundException, SQLException{
		System.out.println("In register handler");
		User user = new User();
		//Validators
		user.setUsername(username);
		user.setPassword(password);
		System.out.println( user.getPassword());
		userDao.update(user);
		return "success.jsp";
	}
	
   
	
	//displaying the data from DB
	@GetMapping("/listofusers")
	public String getAllUser(Model model) {
		System.out.println("getting datas");
		List<User> users = userDao.getAllUsers();
		System.out.println(users);
		model.addAttribute("USER_LIST",users);
		return "listUser.jsp";
	}
	
	@GetMapping("/searchByName")
    public String search(@RequestParam ("userName") String name, Model model)
    {
        User user = new User();
        user.setUsername(name);        
        List<User> users = userDao.search(user);
        model.addAttribute("USER_LIST", users);
        
        return "listUser.jsp";
    }
		
	//to find the user by id
	@GetMapping("/findUserbyId")
	public String findUserById(@RequestParam("userId") Integer userId,Model model) {
		System.out.println("finding");
		String name = userDao.findById(userId);
		System.out.println(name);
		
		if(name == null) {
			throw new EmptyResultDataAccessException("User not found", userId);
		}else {
			System.out.println("User Found" + name);
			
			model.addAttribute("userFound", name);
		}
		return "findSuccess.jsp";
	}
	
	
}
