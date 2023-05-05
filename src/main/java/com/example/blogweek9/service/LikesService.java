package com.example.blogweek9.service;

import com.example.blogweek9.dto.LikeDTO;
import jakarta.servlet.http.HttpSession;

public interface LikesService {
    LikeDTO createLike(LikeDTO likeDTO, Long postId, HttpSession session);
}
