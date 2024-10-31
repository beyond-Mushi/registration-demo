package org.example.adminuserregistrationdemo.model;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CustomAuthenticationProvider implements AuthenticationProvider {

    private final UserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication)
            throws AuthenticationException {
        String userEmail = authentication.getName();
        String userPassword = authentication.getCredentials().toString();
        UserDetails user = userDetailsService.loadUserByUsername(userEmail);

        if (passwordEncoder.matches(userPassword, user.getPassword()) && user != null) {
            return new UsernamePasswordAuthenticationToken(
                    user, userPassword, user.getAuthorities()
            );
        } else {
            throw new BadCredentialsException("Invalid Credentails");
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(
                UsernamePasswordAuthenticationToken.class
        );
    }
}
