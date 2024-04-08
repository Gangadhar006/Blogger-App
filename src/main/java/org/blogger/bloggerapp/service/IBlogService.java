package org.blogger.bloggerapp.service;

import org.blogger.bloggerapp.payload.request.BlogPostsRequestDto;
import org.blogger.bloggerapp.payload.response.BlogPostsResponseDto;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface IBlogService {
    BlogPostsResponseDto createBlog(BlogPostsRequestDto blogPostsRequestDto) throws IOException;

    BlogPostsResponseDto editBlog(BlogPostsRequestDto blogPostsRequestDto, Long blogId);

    String deleteBlog(Long blogId);

    BlogPostsResponseDto fetchBlog(Long blogId);

    List<BlogPostsResponseDto> fetchBlogByUser();

    String uploadImage(MultipartFile image, Long blogId) throws IOException;
}
