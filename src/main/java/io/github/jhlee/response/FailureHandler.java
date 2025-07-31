package io.github.jhlee.response;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@Slf4j
public class FailureHandler implements AuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        log.error("onAuthenticationFailure; error={}", exception.getMessage());

        exception.printStackTrace();
        response.sendRedirect("/login?error=true");

    }

    /*@Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType("application/json; charset=UTF-8");

        String body = new ObjectMapper().writeValueAsString(Map.of(
                "code", 401,
                "message", "아이디 또는 비밀번호가 올바르지 않습니다."
        ));
        response.getWriter().write(body);
    }*/
}
