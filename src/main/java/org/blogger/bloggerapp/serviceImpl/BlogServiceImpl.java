package org.blogger.bloggerapp.serviceImpl;

import lombok.RequiredArgsConstructor;
import org.blogger.bloggerapp.entity.BlogPosts;
import org.blogger.bloggerapp.entity.Users;
import org.blogger.bloggerapp.exception.BlogNotFoundException;
import org.blogger.bloggerapp.exception.UserNotFoundException;
import org.blogger.bloggerapp.payload.request.BlogPostsRequestDto;
import org.blogger.bloggerapp.payload.response.BlogPostsResponseDto;
import org.blogger.bloggerapp.repository.IBlogPostsRepository;
import org.blogger.bloggerapp.repository.IUsersRepository;
import org.blogger.bloggerapp.service.IBlogService;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static org.blogger.bloggerapp.constants.AppConstants.*;

@Service
@RequiredArgsConstructor
public class BlogServiceImpl implements IBlogService {
    private final IBlogPostsRepository blogRepo;
    private final IUsersRepository userRepo;
    private final ModelMapper mapper;

    @Override
    public BlogPostsResponseDto createBlog(BlogPostsRequestDto blogPostsRequestDto) {
        BlogPosts blog = mapper.map(blogPostsRequestDto, BlogPosts.class);
        Users user = currentLoginUser();
        blog.setUser(user);
        blog = blogRepo.save(blog);
        return mapper.map(blog, BlogPostsResponseDto.class);
    }

    @Override
    public BlogPostsResponseDto editBlog(BlogPostsRequestDto blogPostsRequestDto, Long blogId) {
        BlogPosts blog = getBlog(blogId);
        blogPostsRequestDto.setUser(blog.getUser());
        blogPostsRequestDto.setImageData(blog.getImageData());
        mapper.map(blogPostsRequestDto, blog);
        blog = blogRepo.save(blog);
        return mapper.map(blog, BlogPostsResponseDto.class);
    }

    @Override
    public String deleteBlog(Long blogId) {
        BlogPosts blog = getBlog(blogId);
        blogRepo.delete(blog);
        return BLOG_DELETE_MESSAGE;
    }

    @Override
    public BlogPostsResponseDto fetchBlog(Long blogId) {
        BlogPosts blog = getBlog(blogId);
        return mapper.map(blog, BlogPostsResponseDto.class);
    }

    @Override
    public List<BlogPostsResponseDto> fetchBlogByUser() {
        Users user = currentLoginUser();
        List<BlogPosts> blogPosts = blogRepo.findByUser(user);
        return blogPosts.stream()
                .map(blogPost -> mapper.map(blogPost, BlogPostsResponseDto.class))
                .toList();
    }

    @Override
    public String uploadImage(MultipartFile image, Long blogId) throws IOException {
        BlogPosts blog = getBlog(blogId);
        blog.setImageData(image.getBytes());
        blogRepo.save(blog);
        return Arrays.toString(blog.getImageData());
    }

    private BlogPosts getBlog(Long blogId) {
        return blogRepo.findById(blogId)
                .orElseThrow(() -> new BlogNotFoundException(BLOG_NOT_FOUND_MESSAGE));
    }

    private Users currentLoginUser() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        return userRepo.findByEmail(email).orElseThrow(
                () -> new UserNotFoundException(USER_NOT_FOUND_MESSAGE)
        );
    }
}