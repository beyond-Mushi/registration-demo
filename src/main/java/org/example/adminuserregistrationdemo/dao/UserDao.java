package org.example.adminuserregistrationdemo.dao;

import org.example.adminuserregistrationdemo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDao extends JpaRepository<User, Integer> {

    User findUserByEmail(String email);
}
