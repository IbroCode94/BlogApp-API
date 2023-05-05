package com.example.blogweek9.dto;

import lombok.*;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class PostDTO {
    private String title;
    private String category;
    private String content;
    private String img_url;
    private Set<CommentsDTO> comments;
}
