package com.t3q.web.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.t3q.dao.AddressDao;
import com.t3q.jpa.model.Address;
import com.t3q.jpa.model.AddressPK;

@Controller
@RequestMapping("/address")
public class AddressController {

	@Resource
	private AddressDao addressDao;

	
	@RequestMapping("/create")
	@ResponseBody
	public String create(String email, String address, String comments) {
		Address add = null;
		try {
			add = new Address(email, address, comments);
			addressDao.save(add);
		} catch (Exception ex) {
			ex.printStackTrace();
			return "Error creating the address: " + ex.toString();
		}
		return "Address succesfully created! (id = " + add.getId().getEmail() + ")";
	}

	/**
	 * /delete --> Delete the address having the passed id.
	 * 
	 * @param id
	 *            The id of the address to delete
	 * @return A string describing if the address is succesfully deleted or not.
	 */
	@RequestMapping("/delete")
	@ResponseBody
	public String delete(String email, String address) {
		try {
			Address add = new Address(new AddressPK(email, address));
			//addressDao.delete(add);
			addressDao.delete(new AddressPK(email,address));
		} catch (Exception ex) {
			ex.printStackTrace();
			return "Error deleting the address:" + ex.toString();
		}
		return "Address succesfully deleted!";
	}

	/**
	 * /get-by-email --> Return the id for the address having the passed email.
	 * 
	 * @param email
	 *            The email to search in the database.
	 * @return The address id or a message error if the address is not found.
	 */
	@RequestMapping("/get-by-email")
	@ResponseBody
	public List<Address> getByEmail(String email) {
		List<Address> addresses = addressDao.findByIdEmail(email);
		return addresses;
	}

	@RequestMapping("/update")
	@ResponseBody
	public String updateAddresscreate(String email, String address, String comments) {
		try {
			Address add = addressDao.findById(new AddressPK(email, address));
			if(comments!=null)add.setComments(comments);

			addressDao.save(add);
		} catch (Exception ex) {
			ex.printStackTrace();
			return "Error updating the address: " + ex.toString();
		}
		return "Address succesfully updated!";
	}

	@RequestMapping("/addresses")
	public @ResponseBody List<Address> getAddressList() {
		List<Address> addresses = addressDao.findAll();
		for (int i = 0; i < addresses.size(); i++) {
			Address add = (Address) addresses.get(i);
			System.out.println(add.toString());
		}
		return addresses;
	}

	@RequestMapping("/joinSQL")
	@ResponseBody
	public List<Address> joinSQL(String email) {
		List<Address> addresses = (List<Address>) addressDao.joinSQL(email);
		for (int i = 0; i < addresses.size(); i++) {
			Address add = (Address) addresses.get(i);
			System.out.println(add.toString());
		}
		return addresses;

	}

} // class AddressController