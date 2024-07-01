package com.prac.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.prac.entity.User;

import jakarta.transaction.Transactional;

public interface UserRepository extends JpaRepository<User, Integer> {

	public User findByUname(String uname);

	@Query(value = "select * from user where email = :e", nativeQuery = true)
	public User getUserEmail(@Param("e") String email);

	@Modifying
	@Transactional
	@Query(value = "delete from user where uname = :uname",nativeQuery = true)
	public void deleteUserByName(String uname);

}
