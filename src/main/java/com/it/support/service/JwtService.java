package com.it.support.service;

import com.it.support.enums.Role;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import static java.time.Instant.now;

@Component
public class JwtService {

    public static final String SECRET = "357638792F423F4428472B4B6250655368566D597133743677397A2443264629";

    /**
     * Extracts the username from a JWT token.
     *
     * @param token The JWT token.
     * @return The username extracted from the token.
     */
    public String extractUsername(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(SECRET)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    /**
     * Extracts the expiration date from a JWT token.
     *
     * @param token The JWT token.
     * @return The expiration date of the token.
     */
    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    /**
     * Extracts a specific claim from a JWT token using a claims resolver function.
     *
     * @param token          The JWT token.
     * @param claimsResolver A function that defines which claim to extract.
     * @param <T>            The type of the claim to extract.
     * @return The extracted claim.
     */
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    /**
     * Extracts all claims from a JWT token.
     *
     * @param token The JWT token.
     * @return All claims present in the token.
     */
    private Claims extractAllClaims(String token) {
        return Jwts
                .parserBuilder()
                .setSigningKey(getSignKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    /**
     * Checks if a JWT token has expired.
     *
     * @param token The JWT token.
     * @return True if the token has expired, false otherwise.
     */
    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    /**
     * Validates a JWT token against user details.
     *
     * @param token       The JWT token to validate.
     * @param userDetails The user details to validate against.
     * @return True if the token is valid and not expired, false otherwise.
     */
    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    /**
     * Generates a new JWT token for a given username and role.
     *
     * @param username The username for which the token is generated.
     * @param role     The role to include in the token claims.
     * @return The generated JWT token.
     */
    public String generateToken(String username, Role role) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .claim("role", role)
                .setExpiration(Date.from(now().plus(20, ChronoUnit.DAYS)))
                .signWith(SignatureAlgorithm.HS256, SECRET)
                .compact();
    }

    /**
     * Creates a new JWT token with the given claims and username.
     *
     * @param claims   The claims to include in the token.
     * @param username The username to set as the subject of the token.
     * @return The created JWT token.
     */
    private String createToken(Map<String, Object> claims, String username) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(username)
                .setIssuedAt(Date.from(now()))
                .setExpiration(Date.from(now().plus(20, ChronoUnit.DAYS)))
                .signWith(getSignKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    /**
     * Retrieves the signing key used to sign the JWT tokens.
     *
     * @return The signing key.
     */
    private Key getSignKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
