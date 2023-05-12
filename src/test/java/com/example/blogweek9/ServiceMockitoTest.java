package com.example.blogweek9;

import com.example.blogweek9.dto.UserDTO;
import com.example.blogweek9.entity.User;
import com.example.blogweek9.enums.Role;
import com.example.blogweek9.repository.UserRepository;
import com.example.blogweek9.service.serviceImpl.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = {ServiceMockitoTest.class})
@RunWith(MockitoJUnitRunner.class)
public class ServiceMockitoTest {
    @Mock
    private UserRepository userRepository;
    @Mock
    private  ModelMapper mapper;

    @InjectMocks
    private UserServiceImpl userService;
    @Test
    public void creatUser(){
        UserDTO userDTO  = new UserDTO();
        userDTO.setUserName("Ibrahim");
        userDTO.setEmail("Ibro@gmail.com");
        userDTO.setPassword("12345");
        userDTO.setRole(Role.USER);

        User savedUser = new User();
        savedUser.setUserName(userDTO.getUserName());
        savedUser.setEmail(userDTO.getEmail());
        savedUser.setPassword(userDTO.getPassword());
        savedUser.setRole(userDTO.getRole());


        when(userRepository.findByEmail(userDTO.getEmail())).thenReturn(Optional.empty());
        when(userRepository.save(any(User.class))).thenReturn(savedUser);


        assertEquals(mapper.map(userRepository.save(savedUser),UserDTO.class),userService.createUser(userDTO));



    }
}
