package org.blogger.bloggerapp.repository;

import org.blogger.bloggerapp.entity.Votes;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VotesRepository extends JpaRepository<Votes, Long> {
}