package com.desafiopitang.usercar.domain.service.impl;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.desafiopitang.usercar.domain.dto.UserDTO;
import com.desafiopitang.usercar.domain.model.Car;
import com.desafiopitang.usercar.domain.model.User;
import com.desafiopitang.usercar.domain.repository.CarResipository;
import com.desafiopitang.usercar.domain.repository.UserRepository;
import com.desafiopitang.usercar.domain.service.UserService;
@Service
public class UserServiceImpl implements UserService, UserDetailsService{
	
	@Autowired
	private UserRepository userRepository;

	
	@Override
	@Transactional
	public User save(User usr) {
		
		return userRepository.save(usr);
	}

	@Override
	public Optional<User> findUserByID(Long id) {
		
		return userRepository.findById(id);
	}

	@Override
	public User upDate(Long id, UserDTO userDto) {
		
		return null;
	}

	@Override
	public List<User> findUsers() {
		return userRepository.findAll();
	}

	@Override
	public void delete(Long id) {
		userRepository.deleteById(id);		
	}

	@Override
	public boolean existsByEmail(String email) {
		return userRepository.existsByEmail(email);
	}

	@Override
	public boolean existsByLogin(String login) {
		return userRepository.existsByLogin(login);
	}

//	public User findByLogin(String login) {
//		return userRepository.findByLogin(login);
//	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return userRepository.findByLogin(username);
	}
	
	

}
