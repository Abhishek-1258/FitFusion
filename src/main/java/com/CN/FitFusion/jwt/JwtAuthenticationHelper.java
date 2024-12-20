package com.CN.FitFusion.jwt;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.spec.SecretKeySpec;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;



import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtAuthenticationHelper {
	
	String secretKey = "";
	private static final long JWT_TOKEN_VALIDITY = 60*60;
	
	public String getUserNameFromToken(String token) {
		Claims claims = getClaims(token);
		return claims.getSubject();
	}
	
	public Claims getClaims(String token) {
		Claims claims = Jwts.parserBuilder().setSigningKey(secretKey.getBytes())
				.build().parseClaimsJws(token).getBody();
		
		return claims;
	}
	
	public boolean isTokenExpired(String token) {
		Claims claims = getClaims(token);
		Date expiryDate = claims.getExpiration();
		return expiryDate.before(new Date());
	}

public String generateToken(UserDetails userDetails) {
		
		Map<String,Object> claims = new HashMap<>();
		
		return Jwts.builder().setClaims(claims).setSubject(userDetails.getUsername())
		.setIssuedAt(new Date(System.currentTimeMillis()))
		.setExpiration(new Date(System.currentTimeMillis()+JWT_TOKEN_VALIDITY*1000))
		.signWith(new SecretKeySpec(secretKey.getBytes(),SignatureAlgorithm.HS512.getJcaName()),SignatureAlgorithm.HS512)
		.compact();
	}

}
