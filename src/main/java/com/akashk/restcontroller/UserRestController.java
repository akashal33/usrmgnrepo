package com.akashk.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.akashk.binding.LoginInfo;
import com.akashk.binding.UnlockAccount;
import com.akashk.binding.UserForm;
import com.akashk.service.UserService;

@RestController
@RequestMapping("/user")
public class UserRestController {

	@Autowired
	private UserService userService;

	@PostMapping("/register")
	public ResponseEntity<String> registerUser(@RequestBody UserForm userForm) {
		 String status = userService.register(userForm);
		 return new ResponseEntity<>(status,HttpStatus.CREATED);
	}

	@PostMapping("/unlock")
	public ResponseEntity<String> unLockAccount(@RequestBody UnlockAccount unlockAccount) {
		return  new ResponseEntity<>(userService.unLockAccount(unlockAccount),HttpStatus.OK);
	}

	@GetMapping("/forgotpassword/{email}")
	public ResponseEntity<String> forgotPassword(@PathVariable String email) {
		return new ResponseEntity<>(userService.forgotPassword(email),HttpStatus.OK);
	}
	
	@GetMapping("/checkemail/{email}")
	public ResponseEntity<String> validEmailCheck(@PathVariable String email) {
		return new ResponseEntity<>(userService.checkValidEmail(email),HttpStatus.OK);
	}

	@GetMapping("/login")
	public ResponseEntity<String> logIn(@RequestBody LoginInfo log) throws Exception {
		return new ResponseEntity<> (userService.logIn(log),HttpStatus.OK);
	}

	@GetMapping("/countries")
	public ResponseEntity<List<String>> getCountries() {
		return new ResponseEntity<>(userService.getCountries(),HttpStatus.OK);
	}

	@GetMapping("/states/{countryName}")
	public ResponseEntity<List<String>> getStates(@PathVariable String countryName) {
		return new ResponseEntity<>(userService.getStates(countryName),HttpStatus.OK);
	}

	@GetMapping("/cities/{stateName}")
	public ResponseEntity<List<String>> getCities(@PathVariable String stateName) {
		return  new ResponseEntity<>(userService.getCities(stateName),HttpStatus.OK);
	}

	

}
