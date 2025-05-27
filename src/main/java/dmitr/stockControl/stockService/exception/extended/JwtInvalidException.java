package dmitr.stockControl.stockService.exception.extended;

import dmitr.stockControl.stockService.exception.base.UnauthorizedException;

public class JwtInvalidException extends UnauthorizedException {

    public JwtInvalidException() {
        super("auth.jwt.invalid");
    }
}
