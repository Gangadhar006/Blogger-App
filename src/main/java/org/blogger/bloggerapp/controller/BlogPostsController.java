package org.blogger.bloggerapp.controller;

import lombok.RequiredArgsConstructor;
import org.apache.catalina.connector.Response;
import org.blogger.bloggerapp.entity.BlogPosts;
import org.blogger.bloggerapp.payload.request.BlogPostsRequestDto;
import org.blogger.bloggerapp.payload.response.BlogPostsResponseDto;
import org.blogger.bloggerapp.repository.IBlogPostsRepository;
import org.blogger.bloggerapp.service.IBlogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URI;
import java.util.List;

import static org.blogger.bloggerapp.constants.ApiEndpointConstants.*;

@RestController
@RequestMapping(BLOGPOSTS_BASE_URL)
@RequiredArgsConstructor
public class BlogPostsController {

    private final IBlogService blogService;
    private final IBlogPostsRepository blogRepo;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @PostMapping
    public ResponseEntity<BlogPostsResponseDto> createBlog(@RequestBody BlogPostsRequestDto blogPostsRequestDto) throws IOException {
        return ResponseEntity.status(HttpStatus.CREATED).body(blogService.createBlog(blogPostsRequestDto));
    }

    @PostMapping(BLOGPOSTS_IMAGE_URL)
    public ResponseEntity<String> uploadImage(@RequestParam MultipartFile image,
                                              @PathVariable Long blogId) throws IOException {
        return ResponseEntity.status(HttpStatus.OK).body(blogService.uploadImage(image, blogId));
    }

    @PutMapping(BLOGPOSTS_ID_URL)
    public ResponseEntity<BlogPostsResponseDto> editBlog(@RequestBody BlogPostsRequestDto blogPostsRequestDto,
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

    @GetMapping()
    public ResponseEntity<List<BlogPostsResponseDto>> fetchBlogByUser() {
        return ResponseEntity.status(HttpStatus.FOUND).body(blogService.fetchBlogByUser());
    }

    @GetMapping(BLOGPOSTS_IMAGE_URL)
    public ResponseEntity<byte[]> fetchImage(@PathVariable Long blogId) {
        BlogPosts post = blogRepo.findById(blogId).get();
        return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(post.getImageData());
    }
}