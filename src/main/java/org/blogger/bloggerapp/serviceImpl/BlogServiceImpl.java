package org.blogger.bloggerapp.serviceImpl;

import lombok.RequiredArgsConstructor;
import org.blogger.bloggerapp.entity.BlogPosts;
import org.blogger.bloggerapp.entity.Tags;
import org.blogger.bloggerapp.entity.Users;
import org.blogger.bloggerapp.payload.TagsDto;
import org.blogger.bloggerapp.payload.request.BlogPostsRequestDto;
import org.blogger.bloggerapp.payload.response.BlogPostsResponseDto;
import org.blogger.bloggerapp.repository.IBlogPostsRepository;
import org.blogger.bloggerapp.repository.ITagsRepository;
import org.blogger.bloggerapp.service.IBlogService;
import org.blogger.bloggerapp.utility.AppUtils;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.blogger.bloggerapp.constants.AppConstants.BLOG_DELETE_MESSAGE;

@Service
@RequiredArgsConstructor
public class BlogServiceImpl implements IBlogService {
    private final IBlogPostsRepository blogRepo;
    private final ITagsRepository tagRepo;
    private final AppUtils appUtils;
    private final ModelMapper mapper;

    @Override
    public BlogPostsResponseDto createBlog(BlogPostsRequestDto blogPostsRequestDto) {
        BlogPosts blog = mapper.map(blogPostsRequestDto, BlogPosts.class);
        Users user = appUtils.currentLoginUser();
        blog.setUser(user);
        blog = blogRepo.save(blog);
        return mapper.map(blog, BlogPostsResponseDto.class);
    }

    @Override
    public BlogPostsResponseDto editBlog(BlogPostsRequestDto blogPostsRequestDto, Long blogId) {
        BlogPosts blog = appUtils.getBlog(blogId);
        blogPostsRequestDto.setUser(blog.getUser());
        blogPostsRequestDto.setImageData(blog.getImageData());
        mapper.map(blogPostsRequestDto, blog);
        blog = blogRepo.save(blog);
        return mapper.map(blog, BlogPostsResponseDto.class);
    }

    @Override
    public String deleteBlog(Long blogId) {
        BlogPosts blog = appUtils.getBlog(blogId);
        blogRepo.delete(blog);
        return BLOG_DELETE_MESSAGE;
    }

    @Override
    public BlogPostsResponseDto fetchBlog(Long blogId) {
        BlogPosts blog = appUtils.getBlog(blogId);
        return mapper.map(blog, BlogPostsResponseDto.class);
    }

    @Override
    public List<BlogPostsResponseDto> fetchBlogByUser() {
        Users user = appUtils.currentLoginUser();
        List<BlogPosts> blogPosts = blogRepo.findByUser(user);
        return blogPosts.stream()
                .map(blogPost -> mapper.map(blogPost, BlogPostsResponseDto.class))
                .toList();
    }

    @Override
    public TagsDto saveTagsToPost(Long blogId, TagsDto tagsDto) {
        Set<String> tagsSet = Arrays.stream(tagsDto.getTags().split(",")).collect(Collectors.toSet());
        String uniqueTags = "";
        for (String tag : tagsSet)
            uniqueTags = uniqueTags.concat(tag.concat(","));
        tagsDto.setTags(uniqueTags);
        Tags tags = mapper.map(tagsDto, Tags.class);
        tags.setPost(List.of(appUtils.getBlog(blogId)));
        tags = tagRepo.save(tags);
        return mapper.map(tags, TagsDto.class);
    }

    @Override
    public List<TagsDto> fetchTagsFromPost(Long blogId) {
        List<Tags> tags = tagRepo.findByPostId(blogId);
        return tags.stream().map(tag -> mapper.map(tag, TagsDto.class)).toList();
    }
}