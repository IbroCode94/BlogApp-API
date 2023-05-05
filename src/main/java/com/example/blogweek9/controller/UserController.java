package com.example.blogweek9.controller;

import com.example.blogweek9.dto.UserDTO;
import com.example.blogweek9.service.UserService;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }
    @PostMapping("/register")
    public ResponseEntity<Object> createUser(@RequestBody UserDTO userDTO, HttpServletRequest httpServletRequest){

       return  new ResponseEntity<>(userService.createUser(userDTO), HttpStatus.CREATED);
    }
    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestBody UserDTO userDTO){
        UserDTO userDTO1 = userService.loginUser(userDTO);
        return new ResponseEntity<>(userDTO1.getUserName(), HttpStatus.OK);
    }
    @GetMapping("/all")
    public List<UserDTO> getallUsers(){
        return userService.getALllUser();
    }
    @PostMapping("/logout")
    public ResponseEntity<String> logout(HttpSession session){
        session.setAttribute("userId",null);
        return   new ResponseEntity<>("You have been logged out",HttpStatus.GONE);
    }

    @PostMapping("/logout/admin")
    public ResponseEntity<String> adminLogout(HttpSession session){
        session.setAttribute("id",null);
        return   new ResponseEntity<>("You have been logged out",HttpStatus.GONE);
    }
}
