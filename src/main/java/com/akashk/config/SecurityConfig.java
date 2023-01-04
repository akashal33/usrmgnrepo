package com.akashk.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.akashk.filter.JWTSecurityFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	private static final String ADMIN = "ADMIN";
	private static final String USER = "USER";

	@Autowired
	private UserDetailsService userDetailsService;
	@Autowired
	private JWTSecurityFilter jwtFilter;

	@Autowired
	public void authManager(AuthenticationManagerBuilder builder) throws Exception {

		builder.userDetailsService(userDetailsService).passwordEncoder(NoOpPasswordEncoder.getInstance());

	}

	@Bean
	public SecurityFilterChain chain(HttpSecurity security) throws Exception {
		security.csrf().disable();
		security.authorizeHttpRequests()
				.antMatchers("/user/**")
				.permitAll().antMatchers("/home").hasAnyAuthority(ADMIN,USER).antMatchers("/admin").hasAuthority(ADMIN)
				.anyRequest().authenticated()
				.and()
				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
				.and()
				.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
		/*
		 * /login", "/register", "/unlock", "/forgotpassword/{email}", "/checkemail/{
		 * email}", "/countries", "/states/{countryId}", "/cities/{stateId}
		 */
		return security.build();

	}

	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
		return configuration.getAuthenticationManager();
	}

}
