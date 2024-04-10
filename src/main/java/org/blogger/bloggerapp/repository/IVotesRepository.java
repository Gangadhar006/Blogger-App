package org.blogger.bloggerapp.repository;

import org.blogger.bloggerapp.entity.BlogPosts;
import org.blogger.bloggerapp.entity.Votes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface IVotesRepository extends JpaRepository<Votes, Long> {
    @Query(value = "SELECT SUM(up_vote) FROM VOTES WHERE post_id=:blogId", nativeQuery = true)
    Long upVoteCountByPost(Long blogId);

    @Query(value = "SELECT SUM(down_vote) FROM VOTES WHERE post_id=:blogId", nativeQuery = true)
    Long downVoteCountByPost(Long blogId);
}