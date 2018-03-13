package com.machowina.service;

import com.machowina.model.User;

public interface UserService {

	User findUserForCar(Long carId);
}
