package org.blogger.bloggerapp.repository;

import org.blogger.bloggerapp.entity.Tags;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ITagsRepository extends JpaRepository<Tags, Long> {
    List<Tags> findByPostId(Long blogId);
    @Query(value = "SELECT post_id FROM tags_post WHERE tags_id IN(SELECT id FROM tags WHERE tags like %:tag%)", nativeQuery = true)
    List<Long> findByTagName(String tag);
}