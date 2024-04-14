package org.blogger.bloggerapp.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.blogger.bloggerapp.payload.TagsDto;
import org.blogger.bloggerapp.payload.request.BlogPostsRequestDto;
import org.blogger.bloggerapp.payload.response.BlogPostsResponseDto;
import org.blogger.bloggerapp.repository.IBlogPostsRepository;
import org.blogger.bloggerapp.service.IBlogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

import static org.blogger.bloggerapp.constants.ApiEndpointConstants.*;

@RestController
@RequestMapping(BLOGPOSTS_BASE_URL)
@RequiredArgsConstructor
public class BlogPostsController {

    private final IBlogService blogService;

    @PostMapping
    public ResponseEntity<BlogPostsResponseDto> createBlog(@Valid @RequestBody BlogPostsRequestDto blogPostsRequestDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(blogService.createBlog(blogPostsRequestDto));
    }

    @PutMapping(BLOGPOSTS_ID_URL)
    public ResponseEntity<BlogPostsResponseDto> editBlog(@Valid @RequestBody BlogPostsRequestDto blogPostsRequestDto,
                                                         @PathVariable Long blogId) {
        return ResponseEntity.status(HttpStatus.OK).body(blogService.editBlog(blogPostsRequestDto, blogId));
    }

    @DeleteMapping(BLOGPOSTS_ID_URL)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBlog(@PathVariable Long blogId) {
        blogService.deleteBlog(blogId);
    }

    @GetMapping(BLOGPOSTS_ID_URL)
    public ResponseEntity<BlogPostsResponseDto> fetchBlog(@PathVariable Long blogId) {
        return ResponseEntity.status(HttpStatus.FOUND).body(blogService.fetchBlog(blogId));
    }

    @GetMapping
    public ResponseEntity<List<BlogPostsResponseDto>> fetchBlogByUser() {
        return ResponseEntity.status(HttpStatus.FOUND).body(blogService.fetchBlogByUser());
    }

    @PostMapping(BLOGPOSTS_TAGS_URL)
    public ResponseEntity<TagsDto> saveTagsToPost(@PathVariable Long blogId,
                                                  @Valid @RequestBody TagsDto tagsDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(blogService.saveTagsToPost(blogId, tagsDto));
    }

    @GetMapping(BLOGPOSTS_TAGS_URL)
    public ResponseEntity<List<TagsDto>> fetchTagsFromPost(@PathVariable Long blogId){
        return ResponseEntity.status(HttpStatus.OK).body(blogService.fetchTagsFromPost(blogId));
    }
}