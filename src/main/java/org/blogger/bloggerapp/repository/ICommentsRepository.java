package org.blogger.bloggerapp.repository;

import org.blogger.bloggerapp.entity.Comments;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICommentsRepository extends JpaRepository<Comments, Long> {
}