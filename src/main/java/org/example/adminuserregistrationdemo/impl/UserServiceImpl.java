package org.example.adminuserregistrationdemo.impl;

import lombok.RequiredArgsConstructor;
import org.example.adminuserregistrationdemo.dao.UserDao;
import org.example.adminuserregistrationdemo.dto.UserDto;
import org.example.adminuserregistrationdemo.entity.User;
import org.example.adminuserregistrationdemo.service.UserService;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final UserDao userDao;

    @Override
    public User save(UserDto userDto) {
        User user = new User(
                userDto.getFullName(),
                userDto.getEmail(),
                userDto.getPassword(),
                userDto.getRole()
        );
        return userDao.save(user);
    }
}
