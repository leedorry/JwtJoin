package io.github.jhlee.jwt;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtProvider {

    private final JwtToken jwtToken;
    private SecretKey key;

    @PostConstruct
    public void init() {
        this.key = Keys.hmacShaKeyFor(jwtToken.getSecret().getBytes(StandardCharsets.UTF_8));
    }

    // Access Token 생성
    public String createAccessToken(String username, List<String> roles) {
        return createToken(username, roles, jwtToken.getAccessTokenExpireTime());
    }

    // Refresh Token 생성
    public String createRefreshToken(String username) {
        return createToken(username, Collections.emptyList(), jwtToken.getRefreshTokenExpireTime());
    }

    // 공통 토큰 생성
    public String createToken(String username, List<String> roles, long expireTime) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + expireTime);

        return Jwts.builder()
                .subject(username)
                .claim("roles", roles)
                .issuedAt(now)
                .expiration(expiryDate)
                .signWith(key)
                .compact();
    }

    // 토큰에서 username 추출
    public String getUsername(String token) {
        return Jwts.parser()
                .verifyWith(key)
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();
    }

    // 토큰에서 roles 추출
    @SuppressWarnings("unchecked")
    public List<String> getRoles(String token) {
        Object roles = Jwts.parser()
                .verifyWith(key)
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .get("roles");

        return roles != null ? (List<String>) roles : Collections.emptyList();
    }

    // 토큰 유효성 검사, 인증 정보 추출 등 기존 메서드 유지
    public boolean validateToken(String token) {
        try {
            Jwts.parser().verifyWith(key).build().parseSignedClaims(token);
            return true;
        } catch(JwtException | IllegalArgumentException e) {
            log.error("validateToken; error={}", e.getMessage());
            return false;
        }
    }

/*    public Authentication getAuthentication(String token) {
        Claims claims = Jwts.parser().setSigningKey(key).build().parseClaimsJws(token).getBody();
        String username = claims.getSubject();

        List<String> roles = claims.get("roles", List.class);
        List<GrantedAuthority> authorities = roles == null ? Collections.emptyList() :
                roles.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());

        UserDetails principal = new User(username, "", authorities);

        return new UsernamePasswordAuthenticationToken(principal, token, authorities);
    }*/

}
