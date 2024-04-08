package org.blogger.bloggerapp.serviceImpl;

import lombok.RequiredArgsConstructor;
import org.blogger.bloggerapp.entity.BlogPosts;
import org.blogger.bloggerapp.entity.Users;
import org.blogger.bloggerapp.entity.Votes;
import org.blogger.bloggerapp.exception.BlogNotFoundException;
import org.blogger.bloggerapp.exception.UserNotFoundException;
import org.blogger.bloggerapp.repository.IBlogPostsRepository;
import org.blogger.bloggerapp.repository.IUsersRepository;
import org.blogger.bloggerapp.repository.IVotesRepository;
import org.blogger.bloggerapp.service.IVotesService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import static org.blogger.bloggerapp.constants.AppConstants.BLOG_NOT_FOUND_MESSAGE;
import static org.blogger.bloggerapp.constants.AppConstants.USER_NOT_FOUND_MESSAGE;

@Service
@RequiredArgsConstructor
public class VotesService implements IVotesService {
    private final IVotesRepository votesRepo;
    private final IUsersRepository userRepo;
    private final IBlogPostsRepository blogRepo;

    @Override
    public Long upVote(Long blogId) {
        BlogPosts blog = currentBlogPost(blogId);
        Votes votes = new Votes();
        votes.setUpVote(1L);
        votes.setDownVote(0L);
        votes.setPost(blog);
        votes.setUser(currentLoginUser());
        votesRepo.save(votes);
        return votesRepo.upVoteCountByPost(blogId);
    }

    @Override
    public Long downVote(Long blogId) {
        BlogPosts blog = currentBlogPost(blogId);
        Votes votes = new Votes();
        votes.setDownVote(1L);
        votes.setUpVote(0L);
        votes.setPost(blog);
        votes.setUser(currentLoginUser());
        votesRepo.save(votes);
        return votesRepo.downVoteCountByPost(blogId);
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
