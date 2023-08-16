package com.desafiopitang.usercar.domain.service;

import java.util.List;
import java.util.Optional;

import com.desafiopitang.usercar.domain.dto.UserDTO;
import com.desafiopitang.usercar.domain.model.User;

public interface UserService {
	User save(User usr);
	
	Optional<User> findUserByID(Long id);
	
	User upDate(Long id, UserDTO userDto); 
	
	List<User> findUsers();
	
	void delete(Long id);
	
	boolean existsByEmail(String email);
	
	boolean existsByLogin(String login);
	
	//User findByLogin(String login);
}
