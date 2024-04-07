package org.blogger.bloggerapp.repository;

import org.blogger.bloggerapp.entity.BlogPosts;
import org.blogger.bloggerapp.entity.Comments;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentsRepository extends JpaRepository<Comments, Long> {
}