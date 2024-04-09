package org.blogger.bloggerapp.serviceImpl;

import lombok.RequiredArgsConstructor;
import org.blogger.bloggerapp.entity.BlogPosts;
import org.blogger.bloggerapp.entity.Comments;
import org.blogger.bloggerapp.payload.CommentsDto;
import org.blogger.bloggerapp.repository.ICommentsRepository;
import org.blogger.bloggerapp.service.ICommentsService;
import org.blogger.bloggerapp.utility.AppUtils;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements ICommentsService {
    private final ICommentsRepository commentsRepo;
    private final ModelMapper mapper;
    private final AppUtils appUtils;

    @Override
    public CommentsDto postComment(CommentsDto commentsDto, Long blogId) {
        Comments comments = mapper.map(commentsDto, Comments.class);
        comments.setUser(appUtils.currentLoginUser());
        comments.setPost(appUtils.getBlog(blogId));
        comments = commentsRepo.save(comments);
        return mapper.map(comments, CommentsDto.class);
    }

    @Override
    public List<CommentsDto> getComments(Long blogId) {
        BlogPosts blog = appUtils.getBlog(blogId);
        return blog.getComments().stream()
                .map(comment -> mapper.map(comment, CommentsDto.class))
                .toList();
    }
}
