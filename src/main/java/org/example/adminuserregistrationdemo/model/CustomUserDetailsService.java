package org.example.adminuserregistrationdemo.model;

import lombok.RequiredArgsConstructor;
import org.example.adminuserregistrationdemo.dao.UserDao;
import org.example.adminuserregistrationdemo.entity.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserDao userDao;

    @Override
    public UserDetails loadUserByUsername(String userEmail)
            throws UsernameNotFoundException {
        User user = userDao.findUserByEmail(userEmail);

        if (user == null) {
            throw new UsernameNotFoundException("UserNotFound!!!");
        }
        return new CustomUserDetails(user);
    }
}
