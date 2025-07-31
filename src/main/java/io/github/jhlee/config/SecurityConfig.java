package io.github.jhlee.config;

import io.github.jhlee.response.FailureHandler;
import io.github.jhlee.response.SuccessHandler;
import io.github.jhlee.service.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final LoginService loginService;
    private final SuccessHandler successHandler;
    private final FailureHandler failureHandler;

    /**
     *  PasswordEncoder
     * */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable) // CSRF 설정 (필요시 활성화/비활성화)
                .authorizeHttpRequests(auth -> auth // 권한 설정
                        .requestMatchers("/login", "/api/**",
                                "/swagger-ui.html", "/swagger-ui/**", "/api-docs/**", "/webjars/**",
                                "/css/**", "/js/**", "/vendor/**", "/img/**")
                        .permitAll()
                        .anyRequest().authenticated()
                )

                // 로그인
                .formLogin(form -> form
                        .loginPage("/login")
                        .usernameParameter("username")
                        .passwordParameter("password")
                        .successHandler(successHandler)
                        .failureHandler(failureHandler)
                        .permitAll()
                )

                // 로그아웃
                .logout(logout-> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/login")
                        .permitAll()
                );

        return  http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder builder = http
                .getSharedObject(AuthenticationManagerBuilder.class);

        builder
                .userDetailsService(loginService)
                .passwordEncoder(passwordEncoder());

        return builder.build();
    }

    /*public DefaultSecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable) // CSRF 비활성화
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/**").permitAll()
                        .anyRequest().authenticated()
                );
            return http.build();
    }*/

}
