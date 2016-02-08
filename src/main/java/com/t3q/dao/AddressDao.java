package com.t3q.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.t3q.jpa.model.Address;
import com.t3q.jpa.model.AddressPK;

@Transactional
public interface AddressDao extends JpaRepository<Address, AddressPK> {
	
	public List<Address> findByIdEmail(String email);
	public Address findById(AddressPK pk);
	
	@Query(value = "SELECT a.* FROM user u, address a where a.EMAIL = u.EMAIL and a.EMAIL = :email", nativeQuery = true)
	public List<Address> joinSQL(@Param("email") String eamil);

}