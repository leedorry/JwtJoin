/*
package io.github.jhlee.util;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Date;

*/
/**
 * JWT 발급 및 검증
 *
 *//*

@Component
public class JwtUtil {

    private final String SECRET_KEY = "jhlee-screet-key";
    private final long EXPIRATION_TIME = 1000 * 60 * 60; // 1시간
    private final Key key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes());

    */
/**
     * Jwt 토큰 생성
     *
     * @param userName 사용자명
     * @return 토큰값
     *//*

    public String generateToken(String userName) {
        return Jwts.builder()
                .claims()
                .subject(userName)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .and()
                .signWith(key)
                .compact();
    }

    */
/**
     * 토큰에서 사용자 이름(subject) 추출
     *
     * @param token 토큰값
     * @return 사용자명
     *//*

    public String extractUserName(String token) {
        JwtParser parser = Jwts.parser().verifyWith((SecretKey) key).build();
        return parser.parseSignedClaims(token).getPayload().getSubject();
    }

    */
/**
     * 토큰 유효성 검사
     *
     * @param token 토큰값
     * @return 반환값
     *//*

    public boolean isTokenValid(String token) {
        try {
            Jwts.parser().verifyWith((SecretKey) key).build().parseSignedClaims(token);
            return true;
        } catch (JwtException e) {
            return false;
        }
    }




}
*/
