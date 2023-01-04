package com.akashk.service;

import java.util.List;

import com.akashk.binding.LoginInfo;
import com.akashk.binding.UnlockAccount;
import com.akashk.binding.UserForm;

public interface UserService {
	
	public String logIn(LoginInfo loginInfo) throws Exception;
	public String register(UserForm userForm);
	public String unLockAccount(UnlockAccount unlockAcc);
	public String forgotPassword(String email);
	public String checkValidEmail(String email);
	public List<String> getCountries();
	public List<String> getStates(String countryName);
	public List<String> getCities(String stateName);
	

}
