package dmitr.stockControl.stockService.service.impl.auth;

import dmitr.stockControl.stockService.model.auth.AuthUserDto;
import dmitr.stockControl.stockService.service.face.auth.AuthUserTokenExtractorService;
import dmitr.stockControl.stockService.service.face.auth.JwtService;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthUserTokenExtractorServiceImpl implements AuthUserTokenExtractorService {

    private final JwtService jwtService;

    @Override
    public AuthUserDto getAuthUserFromAccessToken(String accessToken) {
        Claims claims = jwtService.extractClaimsFromToken(accessToken);

        UUID userId = UUID.fromString(claims.getSubject());
        List<String> rightLabels = (List<String>) claims.get("rights");

        List<GrantedAuthority> rights = rightLabels.stream()
                .map(rightLabel -> (GrantedAuthority) new SimpleGrantedAuthority(rightLabel))
                .toList();

        return AuthUserDto.builder()
                .id(userId)
                .rights(rights)
                .build();
    }
}
