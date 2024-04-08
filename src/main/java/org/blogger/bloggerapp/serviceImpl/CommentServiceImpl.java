package org.blogger.bloggerapp.serviceImpl;

import lombok.RequiredArgsConstructor;
import org.blogger.bloggerapp.entity.BlogPosts;
import org.blogger.bloggerapp.entity.Comments;
import org.blogger.bloggerapp.entity.Users;
import org.blogger.bloggerapp.exception.BlogNotFoundException;
import org.blogger.bloggerapp.exception.UserNotFoundException;
import org.blogger.bloggerapp.payload.CommentsDto;
import org.blogger.bloggerapp.repository.IBlogPostsRepository;
import org.blogger.bloggerapp.repository.ICommentsRepository;
import org.blogger.bloggerapp.repository.IUsersRepository;
import org.blogger.bloggerapp.service.ICommentsService;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.blogger.bloggerapp.constants.AppConstants.BLOG_NOT_FOUND_MESSAGE;
import static org.blogger.bloggerapp.constants.AppConstants.USER_NOT_FOUND_MESSAGE;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements ICommentsService {
    private final ICommentsRepository commentsRepo;
    private final IUsersRepository userRepo;
    private final IBlogPostsRepository blogRepo;
    private final ModelMapper mapper;

    @Override
    public CommentsDto postComment(CommentsDto commentsDto, Long blogId) {
        Comments comments = mapper.map(commentsDto, Comments.class);
        comments.setUser(currentLoginUser());
        comments.setPost(currentBlogPost(blogId));
        comments = commentsRepo.save(comments);
        return mapper.map(comments, CommentsDto.class);
    }

    @Override
    public List<CommentsDto> getComments(Long blogId) {
        BlogPosts blog = currentBlogPost(blogId);
        return blog.getComments().stream()
                .map(comment -> mapper.map(comment, CommentsDto.class))
                .toList();
    }

    private Users currentLoginUser() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        return userRepo.findByEmail(email).orElseThrow(
                () -> new UserNotFoundException(USER_NOT_FOUND_MESSAGE)
        );
    }

    private BlogPosts currentBlogPost(Long blogId) {
        return blogRepo.findById(blogId).orElseThrow(
                () -> new BlogNotFoundException(BLOG_NOT_FOUND_MESSAGE)
        );
    }
}
