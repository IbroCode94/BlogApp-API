package com.example.blogweek9.repository;

import com.example.blogweek9.dto.PostDTO;
import com.example.blogweek9.dto.UserDTO;
import com.example.blogweek9.entity.Likes;
import com.example.blogweek9.entity.Posts;
import com.example.blogweek9.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LikeRepository  extends JpaRepository<Likes, Long> {
@Query(value ="SELECT *  FROM likes WHERE user_id =?1  AND post_id=?2 ", nativeQuery = true)
    Optional<Likes> findBuyer(Long user, Long posts);
}
