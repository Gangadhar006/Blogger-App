package org.blogger.bloggerapp.repository;

import org.blogger.bloggerapp.entity.Tags;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ITagsRepository extends JpaRepository<Tags, Long> {
}