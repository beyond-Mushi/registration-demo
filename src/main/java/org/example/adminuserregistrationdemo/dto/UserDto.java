package org.example.adminuserregistrationdemo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
public class UserDto {
    private String fullName;
    private String email;
    private String password;
    private String role;
}
