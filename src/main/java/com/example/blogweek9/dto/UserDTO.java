package com.example.blogweek9.dto;

import com.example.blogweek9.enums.Role;

import lombok.*;

@Data
public class UserDTO {
     private  String userName;
     private String email;
     private String password;
     private Role role;


}
