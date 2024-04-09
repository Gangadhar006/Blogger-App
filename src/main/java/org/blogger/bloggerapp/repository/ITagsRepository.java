package org.blogger.bloggerapp.repository;

import org.blogger.bloggerapp.entity.Tags;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ITagsRepository extends JpaRepository<Tags, Long> {
    List<Tags> findByPostId(Long blogId);
}