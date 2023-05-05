package com.example.blogweek9.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CommentsDTO {
    private  Long id;
    private  String content;
    private boolean isAdminComment;

}
