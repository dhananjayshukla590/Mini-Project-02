package com.shukla.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.shukla.entities.CityEntity;
import com.shukla.entities.CountryEntity;
import com.shukla.entities.StateEntity;
import com.shukla.entities.UserEntity;
import com.shukla.repositories.CityRepo;
import com.shukla.repositories.CountryRepo;
import com.shukla.repositories.StateRepo;
import com.shukla.repositories.UserRepo;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private CountryRepo countryRepo;

	@Autowired
	private StateRepo stateRepo;

	@Autowired
	private CityRepo cityRepo;

	@Autowired
	private UserRepo userRepo;

	@Override
	public Map<Integer, String> findCountries() {

		List<CountryEntity> countryList = countryRepo.findAll();
		Map<Integer, String> countries = new HashMap<>();
		for (CountryEntity country1: countryList) {
			countries.put(country1.getCountryId(), country1.getCountryName());
		}
		/*
		  countryList.forEach(country -> { countries.put(country.getCountryId(),
		  country.getCountryName()); });
		 */
		return countries;
	}

	@Override
	public Map<Integer, String> findState(Integer countryId) {

		List<StateEntity> stateList = stateRepo.findByCountryId(countryId);
		Map<Integer, String> states = new HashMap<>();
		
		states=stateList.stream().collect(Collectors.toMap(StateEntity::getStateId,StateEntity::getStateName));
		
		/*
		 stateList.forEach(state -> { states.put(state.getStateId(),
		  state.getStateName()); });
		 */
		return states;

	}

	@Override
	public Map<Integer, String> findCity(Integer stateId) {

		List<CityEntity> cityList = cityRepo.findByStateId(stateId);
		Map<Integer, String> cities = new HashMap<>();
		cityList.forEach(city -> {
			cities.put(city.getCityId(), city.getCityName());
		});
		return cities;

	}

	@Override
	public boolean isEmailUnique(String emailId) {

		UserEntity userDetails = userRepo.findByuserEmail(emailId);
		return userDetails.getUserEmail() == null;

	}

	@Override
	public boolean saveUser(UserEntity userEntity) {

		char[] generatePassword = generatePassword(8);
		String tempPassword=generatePassword.toString();
		userEntity.setPassword(tempPassword);
		userEntity.setAccountStatus("LOCKED");
		UserEntity userDetails = userRepo.save(userEntity);
		return userDetails.getUserId() != null;
	}

	@Override
	public String loginCheck(String email, String password) {
		
		UserEntity userDetails = userRepo.findByUserEmailAndPassword(email, password);
		if (userDetails != null) {
			if (userDetails.getAccountStatus().equalsIgnoreCase("LOCKED")) {
				return "ACCOUNT_LOCKED";
			} else {
				return "LOGIN_SUCESS";
			}
		}
		return "INVALID CREDENTIALS";
	}

	@Override
	public boolean isTempPwdValid(String email, String tempPassword) {
		
		UserEntity userDetails = userRepo.findByUserEmailAndPassword(email, tempPassword);
		return userDetails.getUserId() != null;
	}

	@Override
	public boolean unlockAccount(String email, String newPassword) {
		
		UserEntity userDetails = userRepo.findByuserEmail(email);
		userDetails.setAccountStatus("UNLOCKED");
		userDetails.setPassword(newPassword);
		try {
			userRepo.save(userDetails);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public String forgotPassword(String email) {
		
		UserEntity user = userRepo.findByuserEmail(email);
		if(user!=null) {
			return user.getPassword();
		}
		return null;
	}
	private static char[] generatePassword(int length) {
		
	      String capitalCaseLetters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	      String lowerCaseLetters = "abcdefghijklmnopqrstuvwxyz";
	      String specialCharacters = "!@#$";
	      String numbers = "1234567890";
	      String combinedChars = capitalCaseLetters + lowerCaseLetters + specialCharacters + numbers;
	      Random random = new Random();
	      char[] password = new char[length];

	      password[0] = lowerCaseLetters.charAt(random.nextInt(lowerCaseLetters.length()));
	      password[1] = capitalCaseLetters.charAt(random.nextInt(capitalCaseLetters.length()));
	      password[2] = specialCharacters.charAt(random.nextInt(specialCharacters.length()));
	      password[3] = numbers.charAt(random.nextInt(numbers.length()));
	   
	      for(int i = 4; i< length ; i++) {
	         password[i] = combinedChars.charAt(random.nextInt(combinedChars.length()));
	      }
	      return password;
	   }
}
