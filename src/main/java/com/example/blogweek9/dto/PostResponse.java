package com.example.blogweek9.dto;

import lombok.*;

import java.util.List;
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class PostResponse {
    private List<PostDTO> content;
    private int pageNo;
    private int pageSize;
    private long totalElements;
    private int totalPages;
    private boolean last;
}
