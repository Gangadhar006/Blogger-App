package org.blogger.bloggerapp.repository;

import org.blogger.bloggerapp.entity.BlogPosts;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlogPostsRepository extends JpaRepository<BlogPosts, Long> {
}