package org.example.adminuserregistrationdemo.service;

import org.example.adminuserregistrationdemo.dto.UserDto;
import org.example.adminuserregistrationdemo.entity.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    User save(UserDto userDto);
}