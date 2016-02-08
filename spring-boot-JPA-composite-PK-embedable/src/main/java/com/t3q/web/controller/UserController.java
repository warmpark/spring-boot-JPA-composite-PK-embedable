package com.t3q.web.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.t3q.dao.UserDao;
import com.t3q.jpa.model.User;

/**
 * A class to test interactions with the MySQL database using the UserDao class.
 *
 * @author netgloo
 */
@Controller
@RequestMapping("/user")
public class UserController {

	@Resource
	private UserDao userDao;
	

	/**
	 * /create --> Create a new user and save it in the database.
	 * 
	 * @param email
	 *            User's email
	 * @param name
	 *            User's name
	 * @return A string describing if the user is succesfully created or not.
	 */
	@RequestMapping("/create")
	@ResponseBody
	public String create(String email,Long groupId, String name, Integer age,  String useYN, String regUser) {
		User user = null;
		try {
			user = new User(email, groupId, name, age,  useYN, regUser);
			userDao.save(user);
		} catch (Exception ex) {
			ex.printStackTrace();
			return "Error creating the user: " + ex.toString();
		}
		return "User succesfully created! (id = " + user.getEmail() + ")";
	}

	/**
	 * /delete --> Delete the user having the passed id.
	 * 
	 * @param id
	 *            The id of the user to delete
	 * @return A string describing if the user is succesfully deleted or not.
	 */
	@RequestMapping("/delete")
	@ResponseBody
	public String delete(String email) {
		try {
			User user = new User(email);
			userDao.delete(user);
		} catch (Exception ex) {
			ex.printStackTrace();
			return "Error deleting the user:" + ex.toString();
		}
		return "User succesfully deleted!";
	}

	/**
	 * /get-by-email --> Return the id for the user having the passed email.
	 * 
	 * @param email
	 *            The email to search in the database.
	 * @return The user id or a message error if the user is not found.
	 */
	@RequestMapping("/get-by-email")
	@ResponseBody
	public String getByEmail(String email) {
		String userId;
		try {
			User user = userDao.findByEmail(email);
			userId = String.valueOf(user.getEmail());
		} catch (Exception ex) {
			ex.printStackTrace();
			return "User not found";
		}
		return "The user id is: " + userId;
	}

	/**
	 * /update --> Update the email and the name for the user in the database
	 * having the passed id.
	 * 
	 * @param id
	 *            The id for the user to update.
	 * @param email
	 *            The new email.
	 * @param name
	 *            The new name.
	 * @return A string describing if the user is succesfully updated or not.
	 */
	@RequestMapping("/update")
	@ResponseBody
	public String updateUser(String email,String name, Integer age,  String useYN, String regUser) {
		try {
			User user = userDao.getOne(email);
			if(name!=null)user.setName(name);
			if(age!=null & age.intValue()!=0 )user.setAge(age);
			if(useYN!=null) user.setUseYn(useYN);
			if(regUser!=null) user.setRegUser(regUser);
			userDao.save(user);
		} catch (Exception ex) {
			ex.printStackTrace();
			return "Error updating the user: " + ex.toString();
		}
		return "User succesfully updated!";
	}

	@RequestMapping("/users")
	public @ResponseBody List<User> getUserList() {
		List<User> users = userDao.findAll();
		for (int i = 0; i < users.size(); i++) {
			User add = (User) users.get(i);
			System.out.println(add.toString());
			System.out.println("ADDRESS SIZE="+add.getAddresses().size());
		}
		return users;
	}


} // class UserController