package org.example.adminuserregistrationdemo.config;

import lombok.RequiredArgsConstructor;
import org.example.adminuserregistrationdemo.model.CustomAuthenticationProvider;
import org.example.adminuserregistrationdemo.model.CustomSuccessHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final CustomAuthenticationProvider provider;
    private final CustomSuccessHandler successHandler;
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.csrf(c->c.disable())

            .authenticationProvider(this.provider)

            .authorizeHttpRequests(r -> r.requestMatchers("/admin-page")
                .hasAuthority("ADMIN").requestMatchers("user-page")
                .hasAuthority("USER").requestMatchers("registration", "/css/**").permitAll()
                .anyRequest().authenticated())

                .formLogin(form->form.loginPage("/login")
                        .loginProcessingUrl("/login")
                        .successHandler(this.successHandler)
                        .permitAll())
                .logout(form -> form.invalidateHttpSession(true)
                        .clearAuthentication(true)
                        .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                        .logoutSuccessUrl("/login?logout")
                        .permitAll());

        return http.build();
    }


}
