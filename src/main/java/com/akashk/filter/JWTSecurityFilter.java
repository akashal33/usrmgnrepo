package com.akashk.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.akashk.util.JWTUtil;
@Component
public class JWTSecurityFilter extends OncePerRequestFilter {

	@Autowired
	private JWTUtil jwtUtil;
	@Autowired
	private UserDetailsService userDetailsService;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		String authHeader = request.getHeader("Authorization");
		System.out.println(" auth "+ authHeader);
		String token = null;
		UserDetails userDetails = null;
		String userName = null;
		if (authHeader != null && authHeader.startsWith("Bearer ")) {
			System.out.println("start");
			token = authHeader.substring(7);
			userName = jwtUtil.extractUserName(token);
			System.out.println("am i");
			System.out.println(userName);
		}

		if (userName != null && SecurityContextHolder.getContext().getAuthentication() == null) {
			
			userDetails = userDetailsService.loadUserByUsername(userName);

			if (jwtUtil.isValidToken(token, userDetails)) {
				System.out.println("here ...");
				UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
						userDetails.getUsername(), userDetails.getPassword(), userDetails.getAuthorities());
				authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(authenticationToken);

			}
		}
		
		filterChain.doFilter(request, response);
		
	}

}
