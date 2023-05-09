package com.example.blogweek9.controller;

import com.example.blogweek9.dto.PostDTO;
import com.example.blogweek9.dto.PostResponse;
import com.example.blogweek9.service.PostService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {
    private final PostService postService;


    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping("/new")
    public ResponseEntity<PostDTO> createPost(@RequestBody PostDTO postDTO, HttpServletRequest request){
       return  new ResponseEntity<>(postService.createPost(postDTO, request.getSession()), HttpStatus.CREATED);

    }
    @GetMapping
    public PostResponse getAllPosts(
            @RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNo,
            @RequestParam(value = "pagesize", defaultValue = "10", required = false) int pageSize
    ){
        return postService.getAllPost(pageNo, pageSize);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostDTO> getPostById(@PathVariable(name = "id")  long id){
        return ResponseEntity.ok(postService.getPostById(id));

    }
    @PutMapping("/{id}/comment/{userId}")
    public ResponseEntity<PostDTO> updatedPost(@RequestBody PostDTO postDTO, @PathVariable(name = "id") long id, @PathVariable(value = "userId") long userId){
        PostDTO postResponse = postService.updatePost(postDTO, id, userId);
        return  new ResponseEntity<>(postResponse, HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public  ResponseEntity<String> deletePost(@PathVariable(name = "id") long id){
        postService.deletedPostByID(id);
        return  new ResponseEntity<>("Post entity deleted successfully", HttpStatus.OK);
    }
}
