package org.example.adminuserregistrationdemo.controller;

import lombok.RequiredArgsConstructor;
import org.example.adminuserregistrationdemo.dto.UserDto;
import org.example.adminuserregistrationdemo.impl.UserServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserServiceImpl userServiceImpl;

    @GetMapping("/registration")
    public String getRegistrationPage(Model model) {
        model.addAttribute("user", new UserDto());
        return "registration";
    }

    @PostMapping
    public String saveUser(@ModelAttribute("user")UserDto userDto, Model model) {
        userServiceImpl.save(userDto);
        model.addAttribute("message", "Registration Successful");

        return "login";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/admin-page")
    public String admin() {
        return "admin-page";
    }

    @GetMapping("/user-page")
    public String user() {
        return "user-page";
    }
}
