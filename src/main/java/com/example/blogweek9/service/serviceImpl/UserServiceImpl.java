package com.example.blogweek9.service.serviceImpl;


import com.example.blogweek9.dto.UserDTO;
import com.example.blogweek9.entity.User;
import com.example.blogweek9.enums.Role;
import com.example.blogweek9.exception.UserAlreadyExistExceptions;
import com.example.blogweek9.repository.UserRepository;
import com.example.blogweek9.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
   private final UserRepository userRepository;
   private final HttpServletRequest request;
   private final ModelMapper modelMapper;



    @Override
    public UserDTO createUser(UserDTO userDTO) {

      Optional<User> find = userRepository.findByEmail(userDTO.getEmail());
      if (find.isPresent()){
          throw new UserAlreadyExistExceptions("User already Exist");
      }
      User users = new User();
        users.setUserName(userDTO.getUserName());
        users.setEmail(userDTO.getEmail());
        users.setPassword(userDTO.getPassword());
        users.setRole(userDTO.getRole());
      User newUser = userRepository.save(users);
      UserDTO mapUser = modelMapper.map(newUser,UserDTO.class);
      return mapUser;
    }

    @Override
    public UserDTO loginUser(UserDTO userDTO) {
        User user = userRepository.findByEmail(userDTO.getEmail())
                .orElseThrow(() -> new RuntimeException("User does not exist"));

        HttpSession session = request.getSession();
        if (user.getRole().equals(Role.ADMIN)) {
            session.setAttribute("id", user.getId());
        } else {
            session.setAttribute("userId", user.getId());
        }

        UserDTO mappedUser = modelMapper.map(user, UserDTO.class);
        return mappedUser;
    }



    @Override
    public List<UserDTO> getALllUser() {
        List<User> users = userRepository.findAll();
        List<UserDTO> userDTOs = users.stream()
                .map(user -> modelMapper.map(user, UserDTO.class))
                .collect(Collectors.toList());
        return userDTOs;
    }

}
