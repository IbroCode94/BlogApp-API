package com.example.blogweek9.service;



import com.example.blogweek9.dto.PostDTO;
import com.example.blogweek9.dto.PostResponse;
import jakarta.servlet.http.HttpSession;

import java.util.List;

public interface PostService {
    PostDTO createPost(PostDTO postDTO, HttpSession session);

    PostResponse getAllPost(int pageNo, int pageSize );

  PostDTO getPostById(long id);

  PostDTO updatePost(PostDTO postDTO, long id, long userId);
  void deletedPostByID(long id);
}
