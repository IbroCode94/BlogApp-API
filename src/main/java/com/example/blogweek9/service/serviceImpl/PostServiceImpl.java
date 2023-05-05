package com.example.blogweek9.service.serviceImpl;

import com.example.blogweek9.dto.PostDTO;
import com.example.blogweek9.entity.Posts;
import com.example.blogweek9.entity.User;
import com.example.blogweek9.enums.Role;
import com.example.blogweek9.exception.ResourceNotFoundException;
import com.example.blogweek9.repository.PostRepository;
import com.example.blogweek9.repository.UserRepository;
import com.example.blogweek9.service.PostService;
import jakarta.servlet.http.HttpSession;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {

   private final PostRepository postRepository;
   private  final UserRepository userRepository;
   private  final ModelMapper modelMapper;

    public PostServiceImpl(PostRepository postRepository, UserRepository userRepository, ModelMapper modelMapper) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public PostDTO createPost(PostDTO postDTO, HttpSession session) {
        Long userId = (Long) session.getAttribute("id");

        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User","Id", userId));
        if(user.getRole().equals(Role.ADMIN)){
            Posts posts = maptoEntity(postDTO);
            posts.setUser(user);
            posts.setUpdatedAt(LocalDateTime.now());
            Posts newPost = postRepository.save(posts);
            return mapToDTO(newPost);
        }else {
            return null;
        }
    }



    @Override
    public List<PostDTO> getAllPost() {
        List<Posts> posts = postRepository.findAll();
        return posts.stream().map(this::mapToDTO).collect(Collectors.toList());

    }

    @Override
    public PostDTO getPostById(long id) {
         Posts post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Posts","id", id));
        return mapToDTO(post);
    }

    @Override
    public PostDTO updatePost(PostDTO postDTO, long id, long userId) {
       User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User","Id", userId));
        Posts post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Posts", "id", id));
        if(user.getRole().equals(Role.ADMIN)){
            post.setTitle(postDTO.getTitle());
            post.setContent(postDTO.getContent());
            post.setCategory(postDTO.getCategory());
            post.setImg_url(postDTO.getImg_url());
        }else{
            throw new RuntimeException("You are not an Admin");
        }

        Posts updatedPost = postRepository.save(post);
        return mapToDTO(updatedPost);
    }

    @Override
    public void deletedPostByID(long id) {
        Posts post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Posts", "id", id));
        postRepository.delete(post);
    }

    private  PostDTO mapToDTO(Posts posts){
        PostDTO postDTO = modelMapper.map(posts, PostDTO.class);
//        PostDTO postDTO = new PostDTO();
//        postDTO.setTitle(posts.getTitle());
//        postDTO.setCategory(posts.getCategory());
//        postDTO.setContent(posts.getContent());
//        postDTO.setImg_url(posts.getImg_url());
        return postDTO;
}
private Posts maptoEntity(PostDTO postDTO) {
        Posts   post = modelMapper.map(postDTO, Posts.class);
//    Posts post = new Posts();
//    post.setTitle(postDTO.getTitle());
//    post.setCategory(postDTO.getCategory());
//    post.setContent(postDTO.getContent());
//    post.setImg_url(postDTO.getImg_url());
return post;
}

}
