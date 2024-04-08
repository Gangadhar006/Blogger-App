package org.blogger.bloggerapp.service;

public interface IVotesService {
    Long upVote(Long blogId);

    Long downVote(Long blogId);
}