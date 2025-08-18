package io.github.jhlee.jwt;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "jwt")
@RequiredArgsConstructor
public class JwtToken {

    private String secret;
    private long accessTokenExpireTime;
    private long refreshTokenExpireTime;

}
