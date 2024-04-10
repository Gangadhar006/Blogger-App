package org.blogger.bloggerapp.repository;

import org.blogger.bloggerapp.entity.BlogPosts;
import org.blogger.bloggerapp.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface IBlogPostsRepository extends JpaRepository<BlogPosts, Long> {
    List<BlogPosts> findByUser(Users user);

    List<BlogPosts> findAllByIdIn(List<Long> postIdList);

    List<BlogPosts> findByCreatedAtBetween(LocalDateTime fromDate, LocalDateTime toDate);
}