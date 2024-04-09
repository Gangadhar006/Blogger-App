package org.blogger.bloggerapp.utility;

import lombok.RequiredArgsConstructor;
import org.blogger.bloggerapp.entity.BlogPosts;
import org.blogger.bloggerapp.entity.Users;
import org.blogger.bloggerapp.exception.BlogNotFoundException;
import org.blogger.bloggerapp.exception.UserNotFoundException;
import org.blogger.bloggerapp.repository.IBlogPostsRepository;
import org.blogger.bloggerapp.repository.IUsersRepository;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.context.SecurityContextHolder;

import static org.blogger.bloggerapp.constants.AppConstants.BLOG_NOT_FOUND_MESSAGE;
import static org.blogger.bloggerapp.constants.AppConstants.USER_NOT_FOUND_MESSAGE;

@Configuration
@RequiredArgsConstructor
public class AppUtils {
    private final IBlogPostsRepository blogRepo;
    private final IUsersRepository userRepo;

    public BlogPosts getBlog(Long blogId) {
        return blogRepo.findById(blogId)
                .orElseThrow(() -> new BlogNotFoundException(BLOG_NOT_FOUND_MESSAGE));
    }

    public Users currentLoginUser() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        return userRepo.findByEmail(email).orElseThrow(
                () -> new UserNotFoundException(USER_NOT_FOUND_MESSAGE)
        );
    }
}
