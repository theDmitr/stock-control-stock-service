package dmitr.stockControl.stockService.service.face.auth;

import dmitr.stockControl.stockService.model.auth.AuthUserDto;

public interface AuthUserTokenExtractorService {

    AuthUserDto getAuthUserFromAccessToken(String accessToken);
}
