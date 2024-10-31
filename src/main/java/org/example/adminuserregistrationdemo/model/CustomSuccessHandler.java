package org.example.adminuserregistrationdemo.model;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class CustomSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(
            HttpServletRequest request,
            HttpServletResponse response,
            Authentication authentication) throws IOException, ServletException {

        var authorities = authentication.getAuthorities();
        var roles = authorities.stream().map(a -> a.getAuthority()).findFirst();

        if (roles.orElse("").equals("ADMIN")) {
            response.sendRedirect("/admin-page");
        } else if (roles.orElse("").equals("USER")) {
            response.sendRedirect("/user-page");
        } else {
            response.sendRedirect("/error");
        }
    }
}
