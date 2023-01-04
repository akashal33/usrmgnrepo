package com.akashk.util;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JWTUtil {
	
	private static final String secreat = "akash";

	
	public String generateToken(UserDetails userDetails) {
		
		HashMap<String, Object> claims = new HashMap<>();
				
		return createToken(claims, userDetails.getUsername());
	}
	
	private String createToken(Map<String, Object> claims,String subject) {
		
	 return	 Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
		 .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))
		 .signWith(SignatureAlgorithm.HS256, secreat)
		 .compact();
		
		
	}
	
	public <T> T getClaim(String token, Function <Claims,T> claimResolver) {
		
		return claimResolver.apply(getAllClaims(token));
		
	}
	
	private Claims getAllClaims(String token) {
		
	  return Jwts.parser().setSigningKey(secreat).parseClaimsJws(token).getBody();
		
	}
	
	public String extractUserName(String token) {
		System.out.println("in ");
		return getClaim(token, Claims::getSubject);
		
	}
	
	public Date extractExpiration(String token) {
		
		return getClaim(token, Claims::getExpiration);
		
	}
	
	
	public boolean isTokenExpired(String token) {
		return extractExpiration(token).before(new Date());
	}
	
	public boolean isValidToken(String token,UserDetails details) {
		 
		return extractUserName(token).equals(details.getUsername()) && !isTokenExpired(token);
	}
	
}
