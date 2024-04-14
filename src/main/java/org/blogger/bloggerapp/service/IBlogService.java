package org.blogger.bloggerapp.service;

import org.blogger.bloggerapp.payload.TagsDto;
import org.blogger.bloggerapp.payload.request.BlogPostsRequestDto;
import org.blogger.bloggerapp.payload.response.BlogPostsResponseDto;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface IBlogService {
    BlogPostsResponseDto createBlog(BlogPostsRequestDto blogPostsRequestDto);

    BlogPostsResponseDto editBlog(BlogPostsRequestDto blogPostsRequestDto, Long blogId);

    String deleteBlog(Long blogId);

    BlogPostsResponseDto fetchBlog(Long blogId);

    List<BlogPostsResponseDto> fetchBlogByUser();

    TagsDto saveTagsToPost(Long blogId, TagsDto tagsDto);

    List<TagsDto> fetchTagsFromPost(Long blogId);
}
