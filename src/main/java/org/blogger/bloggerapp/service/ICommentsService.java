package org.blogger.bloggerapp.service;

import org.blogger.bloggerapp.payload.CommentsDto;

import java.util.List;

public interface ICommentsService {
    CommentsDto postComment(CommentsDto commentsDto, Long blogId);

    List<CommentsDto> getComments(Long blogId);
}
