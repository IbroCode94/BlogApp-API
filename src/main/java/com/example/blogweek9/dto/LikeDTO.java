package com.example.blogweek9.dto;

import com.example.blogweek9.entity.Posts;
import com.example.blogweek9.entity.User;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class LikeDTO {
    private boolean like;
    private User user;
    private Posts post;
}
