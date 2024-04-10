package org.blogger.bloggerapp.serviceImpl;

import lombok.RequiredArgsConstructor;
import org.blogger.bloggerapp.entity.BlogPosts;
import org.blogger.bloggerapp.entity.Users;
import org.blogger.bloggerapp.exception.UserNotFoundException;
import org.blogger.bloggerapp.payload.response.BlogPostsResponseDto;
import org.blogger.bloggerapp.repository.IBlogPostsRepository;
import org.blogger.bloggerapp.repository.ITagsRepository;
import org.blogger.bloggerapp.repository.IUsersRepository;
import org.blogger.bloggerapp.service.ISearchService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import static org.blogger.bloggerapp.constants.AppConstants.USER_NOT_FOUND_MESSAGE;

@Service
@RequiredArgsConstructor
public class SearchServiceImpl implements ISearchService {
    private final ITagsRepository tagRepo;
    private final IBlogPostsRepository blogRepo;
    private final IUsersRepository userRepo;
    private final ModelMapper mapper;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public List<BlogPostsResponseDto> searchByTag(String tag) {
        List<Long> postIdList = tagRepo.findByTagName(tag);
        List<BlogPosts> blogPosts = blogRepo.findAllByIdIn(postIdList);
        return blogPosts.stream().map(blog -> mapper.map(blog, BlogPostsResponseDto.class)).toList();
    }

    @Override
    public List<BlogPostsResponseDto> searchByUser(String username) {
        Users user = userRepo.findByUsername(username).orElseThrow(
                () -> new UserNotFoundException(USER_NOT_FOUND_MESSAGE)
        );
        List<BlogPosts> blogPosts = blogRepo.findByUser(user);
        return blogPosts.stream().map(blog -> mapper.map(blog, BlogPostsResponseDto.class)).toList();
    }

    @Override
    public List<BlogPostsResponseDto> searchByDateRange(LocalDate fromDate, LocalDate toDate) {
        LocalDateTime startOfDay = fromDate.atStartOfDay();
        LocalDateTime endOfDay = toDate.atTime(LocalTime.MAX);

        List<BlogPosts> blogPosts = blogRepo.findByCreatedAtBetween(startOfDay, endOfDay);

        for (BlogPosts post : blogPosts)
            logger.info(post.getTitle());

        return blogPosts.stream().map(blog -> mapper.map(blog, BlogPostsResponseDto.class)).toList();
    }
}