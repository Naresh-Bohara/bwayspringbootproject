package com.bway.springbootproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.bway.springbootproject.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {

	// Derived Query Method
	User findByUsernameAndPassword(String un, String pss);

	// Derived Query Method
	User findByUsername(String un);

	// JPQL (JPA Query) - Uses Entity Name and Entity Fields
	@Query("SELECT u FROM User u WHERE u.username = :un AND u.password = :psw")
	User checkUser(@Param("un") String un, @Param("psw") String psw);

	// Native SQL Query - Uses Database Table Name and Column Names
	@Query(value = "SELECT * FROM user_table WHERE username = :un AND password = :psw", nativeQuery = true)
	User checkUserNative(@Param("un") String un, @Param("psw") String psw);

}