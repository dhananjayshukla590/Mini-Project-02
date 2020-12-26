package com.shukla.restcontroller;

import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.shukla.entities.StateEntity;
import com.shukla.entities.UserEntity;
import com.shukla.services.UserService;

@RestController
public class UserRestController {

	@Autowired
	private UserService userService;

	@GetMapping(value = "/loadForm")
	public ResponseEntity<Map<Integer, String>> getLoadFrom() {
		
		Map<Integer, String> countries = userService.findCountries();
		return ResponseEntity.ok(countries);
	}

	@GetMapping("/getState/{countryId}")
	public ResponseEntity<Map<Integer, String>> getState(@PathVariable Integer countryId) {
		
		Map<Integer, String> states = userService.findState(countryId);
		return ResponseEntity.ok(states);

	}

	@GetMapping("/getCitites/{stateId}")
	public ResponseEntity<Map<Integer, String>> getCity(@PathVariable Integer stateId) {
		
		Map<Integer, String> cities = userService.findCity(stateId);
		return ResponseEntity.ok(cities);
	}

	@PostMapping(value = "/registerUser", consumes = { "application/json", "application/xml" },
			                              produces = { "application/json", "application/xml" })
	public String addCustomer(@RequestBody UserEntity user) {
		boolean saveUser = userService.saveUser(user);
		if(saveUser==true) {
			return "User saved succesfuly";
		}else {
			return "Please try Again";
		}
	}
	
	@GetMapping(value = "/unlockedAccount/{email}/{newPassword}")
	public String unlockedAccount(@PathVariable String email,@PathVariable String newPassword) {
		boolean unlockAccount = userService.unlockAccount(email, newPassword);
		if(unlockAccount==true) {
			return "Account Unlocked Successfully";
		}else {
			return "Please try Again";
		}
	}
	
	
	@GetMapping("/getPassword/{email}")
	public String getPassword(@PathVariable String email) {
		return null;
	}
	
	@PostMapping(value = "/loginUser", consumes = {"application/json", "application/xml"})
	public String loginCheck(@RequestBody UserEntity user) {
		
		String email=user.getUserEmail();
		String password=user.getPassword();
		String loginCheck = userService.loginCheck(email, password);
		return loginCheck;
	}
}
