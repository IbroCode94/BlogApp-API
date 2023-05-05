package com.example.blogweek9.controller;

import com.example.blogweek9.dto.LikeDTO;
import com.example.blogweek9.dto.UserDTO;
import com.example.blogweek9.repository.LikeRepository;
import com.example.blogweek9.repository.PostRepository;
import com.example.blogweek9.repository.UserRepository;
import com.example.blogweek9.service.LikesService;
import com.example.blogweek9.service.PostService;
import com.example.blogweek9.service.UserService;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/likes")
public class LikeController {

   private final LikesService likesService;
   private final UserService userService;
   private  final PostService postService;

    public LikeController(LikesService likesService, UserService userService, PostService postService) {
        this.likesService = likesService;
        this.userService = userService;
        this.postService = postService;
    }

    @PostMapping("new/{id}")
    public ResponseEntity<LikeDTO> createLikes(LikeDTO likeDTO, @PathVariable(value = "id") Long postId , HttpServletRequest req){
return new ResponseEntity<>(likesService.createLike(likeDTO, postId, req.getSession()), HttpStatus.CREATED);
    }
}
