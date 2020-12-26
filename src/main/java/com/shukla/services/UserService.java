package com.shukla.services;

import java.util.Map;

import com.shukla.entities.UserEntity;

public interface UserService {

	public Map<Integer, String> findCountries();

	public Map<Integer, String> findState(Integer countryId);

	public Map<Integer, String> findCity(Integer stateId);

	public boolean isEmailUnique(String emailId);

	public boolean saveUser(UserEntity userEntity);

	public String loginCheck(String email, String password);

	public boolean unlockAccount(String email,String newPassword);
	
	public String forgotPassword(String email);
	
	public boolean isTempPwdValid(String email,String newPassword);
}
