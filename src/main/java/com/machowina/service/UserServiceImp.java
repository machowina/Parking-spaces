package com.machowina.service;

import org.springframework.stereotype.Service;

import com.machowina.model.User;
import com.machowina.repository.UserRepository;

@Service
public class UserServiceImp implements UserService {

	private final UserRepository userRepository;
	
	
	public UserServiceImp(UserRepository userRepository) {
		super();
		this.userRepository = userRepository;
	}

	@Override
	public User findUserForCar(Long carId) {
		return userRepository.findOneByCarsId(carId);
	}

}
