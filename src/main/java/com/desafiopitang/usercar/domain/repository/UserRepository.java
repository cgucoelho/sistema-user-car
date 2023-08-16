package com.desafiopitang.usercar.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import com.desafiopitang.usercar.domain.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	
	boolean existsByEmail(String email);
	
	boolean existsByLogin(String login);
	
	//User findByLogin(String login);
	
	UserDetails findByLogin(String login);

}
