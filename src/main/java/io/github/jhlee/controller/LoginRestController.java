package io.github.jhlee.controller;

import io.github.jhlee.jwt.JwtProvider;
import io.github.jhlee.response.ApiResponse;
import io.github.jhlee.response.Status;
import io.github.jhlee.service.LoginService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/auth")
@Slf4j
@RequiredArgsConstructor
public class LoginRestController {

    private final JwtProvider jwtProvider;
    private final LoginService loginService;
    private final PasswordEncoder passwordEncoder;


    @PostMapping("/login")
    public ResponseEntity<ApiResponse<?>> login (@RequestParam String username,
                                                 @RequestParam String password) {
        log.info("login; username={}, password={}", username, password);

        try {
            UserDetails userDetails = loginService.loadUserByUsername(username);

            // password 검증
            if(!passwordEncoder.matches(password, userDetails.getPassword())) {
                return new ResponseEntity<>(
                        ApiResponse.error(Status.INVALID_PASSWORD),
                        Status.INVALID_PASSWORD.getHttpStatus()
                );
            }

            // 역할 정보 추출
            List<String> roles = userDetails.getAuthorities()
                    .stream()
                    .map(GrantedAuthority::getAuthority)
                    .toList();

            String accessToken = jwtProvider.createAccessToken(username, roles);
            String refreshToken = jwtProvider.createRefreshToken(username);

            Map<String, String> tokenMap = Map.of(
                    "accessToken", accessToken,
                    "refreshToken", refreshToken
            );

            return new ResponseEntity<>(
                    ApiResponse.success(Status.OK, tokenMap),
                    Status.OK.getHttpStatus()
            );
        } catch (UsernameNotFoundException e) {
            return new ResponseEntity<>(
                    ApiResponse.error(Status.USER_NOT_FOUND),
                    Status.USER_NOT_FOUND.getHttpStatus()
            );
        }

    }

    @PostMapping("/refresh")
    public ResponseEntity<ApiResponse<?>> refresh(@RequestParam String refreshToken) {
        if(!jwtProvider.validateToken(refreshToken)) {
            return new ResponseEntity<>(
                    ApiResponse.error(Status.UNAUTHORIZED),
                    Status.UNAUTHORIZED.getHttpStatus()
            );
        }

        String username = jwtProvider.getUsername(refreshToken);
        List<String> roles = jwtProvider.getRoles(refreshToken);

        String newAccessToken = jwtProvider.createAccessToken(username, roles);
        Map<String, String> tokenMap = Map.of(
                "accessToken", newAccessToken
        );

        return new ResponseEntity<>(
                ApiResponse.success(Status.OK, tokenMap),
                Status.OK.getHttpStatus()
        );
    }

}
