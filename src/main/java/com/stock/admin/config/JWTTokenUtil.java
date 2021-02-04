package com.stock.admin.config;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import com.stock.admin.utils.CustomUserDetail;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import static com.stock.admin.utils.StockAdminConstants.TOKEN_EXPIRY_HOURS;

@Component
public class JWTTokenUtil implements Serializable {

	private static final long serialVersionUID = -2550185165626007488L;

	public static final long JWT_TOKEN_VALIDITY = TOKEN_EXPIRY_HOURS * 60 * 60;

	@Value("${jwt.secret}")
	private String secret;

	//retrieve username from jwt token
	public String getUsernameFromToken(String token) {
		return getClaimFromToken(token, Claims::getSubject);
	}

	//retrieve expiration date from jwt token
	public Date getExpirationDateFromToken(String token) {
		return getClaimFromToken(token, Claims::getExpiration);
	}

	public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
		final Claims claims = getAllClaimsFromToken(token);
		return claimsResolver.apply(claims);
	}
	
	public Object getClaimFromToken(String token, String key) {
		final Claims claims = getAllClaimsFromToken(token);
		return claims!=null ? claims.get(key):null;
	}
    //for retrieving any information from token we will need the secret key
	private Claims getAllClaimsFromToken(String token) {
		try {
		return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
		}
		catch(Exception e) {
			System.err.println("Error in parsing JWT "+e.getMessage());
			return null;
		}
	}

	//check if the token has expired
	private boolean isTokenExpired(String token) {
		final Date expiration = getExpirationDateFromToken(token);
		return expiration.before(new Date());
	}

	//generate token for user
	public String generateToken(CustomUserDetail userDetails,String shopCode) {
		Map<String, Object> claims = new HashMap<>();
		claims.put("shopCode",shopCode);
		return doGenerateToken(claims, userDetails.getUser().getUsername());
	}

	//while creating the token -
	//1. Define  claims of the token, like Issuer, Expiration, Subject, and the ID
	//2. Sign the JWT using the HS512 algorithm and secret key.
	//3. According to JWS Compact Serialization(https://tools.ietf.org/html/draft-ietf-jose-json-web-signature-41#section-3.1)
	//   compaction of the JWT to a URL-safe string 
	private String doGenerateToken(Map<String, Object> claims, String subject) {

		return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY * 1000))
				.signWith(SignatureAlgorithm.HS512, secret).compact();
	}

	//validate token
	public boolean validateToken(String token, CustomUserDetail userDetails) {
		final String username = getUsernameFromToken(token);
		final String shopCode = getShopCodeFromToken(token);
		final boolean isValidShop = userDetails.getUser().getShopCodes().contains(shopCode);
		return (username.equals(userDetails.getUser().getUsername()) && !isTokenExpired(token) && isValidShop);
	}

	private String getShopCodeFromToken(String token) {
		return (String) getClaimFromToken(token, "shopCode");
	}
}
