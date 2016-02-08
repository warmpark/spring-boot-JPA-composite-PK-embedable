package com.t3q.dao;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.t3q.jpa.model.User;

/**
 * A DAO for the entity User is simply created by extending the CrudRepository
 * interface provided by spring. The following methods are some of the ones
 * available from such interface: save, delete, deleteAll, findOne and findAll.
 * The magic is that such methods must not be implemented, and moreover it is
 * possible create new query methods working only by defining their signature!
 * 
 * @author netgloo
 */
@Transactional
public interface UserDao extends JpaRepository<User, String> {

  /**
   * Return the user having the passed name or null if no user is found.
   * 
   * @param name the user name.
   */
  public User findByName(String name);
  
  public User findByEmail(String email);

  
} 