package it.portfolio.hr.humanResource.utilities;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import it.portfolio.hr.humanResource.models.UserDetailsImpl;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Component
public class TokenUtilities {
    private final String secret = "vRURNqOAwQf2OAe3cminZOzmFVoIoHij";


    public String extractUsername(String token) {
        return extractClaim(token, "sub");
    }

    public String extractRole(String token) {
        return extractClaim(token, "role");
    }

    public String extractClaim(String token, String claimName) {
        final Claims claims = extractAllClaims(token);
        return claims.get(claimName, String.class);
    }

    public Date extractExpiration(String token) {
        return extractAllClaims(token).getExpiration();
    }


    private Claims extractAllClaims(String token) {
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
    }

    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }


    public String createToken(UserDetailsImpl userDetails) {
        System.out.println(userDetails.getCompanyName());
        return Jwts.builder()
                .claim("companyName", userDetails.getCompanyName())
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis())) //DATA INIZIO TOKEN
                .setExpiration(new Date(System.currentTimeMillis() + 86400000)) //TOKEN DURA 1 ORA
                .signWith(SignatureAlgorithm.HS256, secret).compact();
    }

    public Boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
            return true;
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    public String getUsername(String token) {
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody().getSubject();
    }
}
