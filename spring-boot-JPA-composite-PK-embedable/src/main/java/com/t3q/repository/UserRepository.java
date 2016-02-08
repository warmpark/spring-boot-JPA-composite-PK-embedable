package com.t3q.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.t3q.jpa.model.User;

/**
 * 
 * @author parkbyunghoon
 *
 */
public interface UserRepository extends JpaRepository<User, String> {
	
}
