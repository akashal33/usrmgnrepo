package com.akashk.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

import javax.mail.MessagingException;
import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import com.akashk.binding.LoginInfo;
import com.akashk.binding.UnlockAccount;
import com.akashk.binding.UserForm;
import com.akashk.entity.Country;
import com.akashk.entity.Role;
import com.akashk.entity.User;
import com.akashk.repository.CityRepository;
import com.akashk.repository.CountryRepository;
import com.akashk.repository.RoleRepository;
import com.akashk.repository.StateRepository;

import com.akashk.repository.UserRepository;
import com.akashk.util.EmailUtil;
import com.akashk.util.JWTUtil;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepo;
	@Autowired
	private RoleRepository roleRepo;
	@Autowired
	private CountryRepository countryRepo;
	@Autowired
	private StateRepository stateRepo;
	@Autowired
	private CityRepository cityRepo;
	/*
	 * @Autowired private MailService mailService;
	 */
	@Autowired
	private AuthenticationManager authManager;
	@Autowired
	private UserDetailsService userDetailsService;
	@Autowired
	private JWTUtil jwtUtil;
	@Autowired
	private EmailUtil emailUtil;

	/*
	 * @Override public String logIn(LoginInfo loginInfo) {
	 * 
	 * User user = userRepo.findByEmailAndPassword(loginInfo.getUsername(),
	 * loginInfo.getPassword()); if (user == null) { return
	 * "please check username and password"; } if (user.getStatus().equals("lock"))
	 * { return " sorry your acount is locked"; } return " login successful";
	 * 
	 * }
	 */

	@Override
	public String logIn(LoginInfo loginInfo) throws Exception {
		
		try {
		authManager.authenticate(new UsernamePasswordAuthenticationToken(loginInfo.getUsername(), loginInfo.getPassword()));
		}
		catch(Exception e) {
			throw new Exception(" user or password invalid");
		}
		
			UserDetails userDetails = userDetailsService.loadUserByUsername(loginInfo.getUsername());
			
			String token = jwtUtil.generateToken(userDetails);
		
		
		return token;

	}
	
	

	@Override
	@Transactional(rollbackOn = MessagingException.class)
	public String register(UserForm userForm) {

		/*
		 * long checkForEmail = userRepo.checkForEmail(userForm.getEmail()); if
		 * (checkForEmail > 0) { return " email already used for registration"; }
		 */

		User regUser = new User();
		BeanUtils.copyProperties(userForm, regUser);

		Set<Role> roles = regUser.getRoles();

		for (Role allRole : roles) {

			roleRepo.save(allRole);

		}

		regUser.setStatus("lock");
		regUser.setPassword(passwordGenerator());

		User user = userRepo.save(regUser);
		if (user.getUserId() != null) {
			//String body = " your password is " + user.getPassword() + " please change on your next login ";
			//return mailService.sendPasswordMail(user.getEmail(), user.getPassword(), body);
			
			String subject = "registration mail";
			String fileName = "register_mail.txt";
			String body = getMailBody(fileName, user);
			String to = user.getEmail();
			
			try {
				return emailUtil.sendMail(to, subject, body);
			} catch (MessagingException e) {
				//
			}
			
		} 
			return " please try again.. ";
		

	}

	@Override
	public String unLockAccount(UnlockAccount unlockAcc) {

		// long checkForEmail = userRepo.checkForEmail(unlockAcc.getEmail());

		User user = userRepo.findByEmail(unlockAcc.getEmail());

		if (user != null && user.getPassword().equals(unlockAcc.getTempPassword())) {

			user.setPassword(unlockAcc.getNewPassword());
			user.setStatus("unlock");

			userRepo.save(user);

			return " Account unlocked, please proceed with login ";

		} else {
			return "please check temp password ";

		}

	}

	@Override
	public String forgotPassword(String email) {

		// String result = null;

		// long checkForEmail = userRepo.checkForEmail(email);
		User user = userRepo.findByEmail(email);
		if (user != null) {
			//String body = " your password is " + user.getPassword();
			//return mailService.sendPasswordMail(user.getEmail(), user.getPassword(), body);
			String subject = " forgot password ";
			String fileName = "forgot_password.txt";
			String body = getMailBody(fileName, user);
			String to = user.getEmail();
			
			try {
				return emailUtil.sendMail(to, subject, body);
			} catch (MessagingException e) {
				//
			}
		}

		return " this email is not registered ";

	}

	private String passwordGenerator() {

		final String chars = "qwertyuiopasdfghjklzxcvbnm123654789QWERTYUIOPLKJHGFDSAZXCVBNM!@#$%^&*()";
		StringBuilder builder = new StringBuilder();
		SecureRandom random = new SecureRandom();

		for (int i = 0; i < 8; i++) {

			int ranIndex = random.nextInt(chars.length());
			builder.append(chars.charAt(ranIndex));

		}

		return builder.toString();

	}

	@Override
	public String checkValidEmail(String email) {

		long checkForEmail = userRepo.checkForEmail(email);
		if (checkForEmail > 0) {
			return "email alrady used for registeration";
		}
		return " email is valid";
	}

	@Override
	public List<String> getCountries() {
		List<Country> countriesList = countryRepo.findAll();
		ArrayList<String> countries = new ArrayList<>();
		
		countriesList.forEach(country -> countries.add(country.getCountryName()));
		
		return countries;
	}



	@Override
	public List<String> getStates(String countryName) {
		return stateRepo.getStates(countryName);
	}



	@Override
	public List<String> getCities(String stateName) {
		
		return cityRepo.getCities(stateName);
	}


	
	private String getMailBody(String fileName,User user) {
		
		StringBuilder builder = new StringBuilder();
		
		//String path = "C:\\Users\\sneha\\Documents\\angularminiproj\\usermanagment-3\\src\\main\\resources\\"+fileName;
		
		try ( Stream <String> fileLines =   Files.lines(Paths.get(fileName))){
			
			fileLines.forEach(line -> {
				
				line =  line.replace("${firstname}",user.getFirstName());
				line = line.replace("${lastname}", user.getLastName());
				line =	line.replace("${password}", user.getPassword());
				builder.append(line);
			});
			
			
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
		
		
		return builder.toString();
	}



	
	

}
