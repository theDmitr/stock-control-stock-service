package dmitr.stockControl.stockService.service.impl.auth;

import dmitr.stockControl.stockService.exception.extended.JwtInvalidException;
import dmitr.stockControl.stockService.service.face.auth.JwtService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

@Service
public class JwtServiceImpl implements JwtService {

    @Value("${JWT_SECRET_KEY}")
    private String jwtSecretKeyString;
    private Key jwtSecretKey;

    @PostConstruct
    private void init() {
        this.jwtSecretKey = Keys.hmacShaKeyFor(jwtSecretKeyString.getBytes(StandardCharsets.UTF_8));
    }

    @Override
    public Claims extractClaimsFromToken(String token) {
        try {
            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(jwtSecretKey)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();

            Date currentDate = new Date();
            if (claims.getExpiration().before(currentDate)) {
                throw new JwtInvalidException();
            }

            return claims;
        } catch (Exception e) {
            throw new JwtInvalidException();
        }
    }
}
