package com.epam.ems.request;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

import static com.epam.ems.secret.JWTSecretHolder.JWT_SECRET;
import static org.springframework.util.StringUtils.hasText;

@Component
public class RequestProcessor {
    private static final String AUTHORIZATION = "Authorization";
    private static final String BEARER = "Bearer ";

    public String getTokenFromRequest(HttpServletRequest request) {
        String bearer = request.getHeader(AUTHORIZATION);
        if (hasText(bearer) && bearer.startsWith(BEARER)) {
            return bearer.substring(7);
        }
        return new String();
    }

    public String getLoginFromToken(String token) {
        Claims claims = Jwts.parser().setSigningKey(JWT_SECRET).parseClaimsJws(token).getBody();
        return claims.getSubject();
    }
}
