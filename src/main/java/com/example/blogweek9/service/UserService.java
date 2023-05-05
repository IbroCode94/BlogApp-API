package com.example.blogweek9.service;



import com.example.blogweek9.dto.UserDTO;
import jakarta.servlet.http.HttpSession;

import java.util.List;

public interface UserService {
    UserDTO createUser(UserDTO userDTO);
    public List<UserDTO> getALllUser();

    public  UserDTO loginUser(UserDTO userDTO);

}
