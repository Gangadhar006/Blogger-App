package org.blogger.bloggerapp.controller;

import lombok.RequiredArgsConstructor;
import org.blogger.bloggerapp.entity.BlogPosts;
import org.blogger.bloggerapp.repository.IBlogPostsRepository;
import org.blogger.bloggerapp.service.IImageService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

import static org.blogger.bloggerapp.constants.ApiEndpointConstants.BLOGPOSTS_BASE_URL;
import static org.blogger.bloggerapp.constants.ApiEndpointConstants.BLOGPOSTS_IMAGE_URL;

@RestController
@RequestMapping(BLOGPOSTS_BASE_URL)
@RequiredArgsConstructor
public class ImageController {
    private final IImageService imageService;

    @GetMapping(BLOGPOSTS_IMAGE_URL)
    public ResponseEntity<byte[]> fetchImage(@PathVariable Long blogId) {
        return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(imageService.fetchImage(blogId));
    }

    @PostMapping(BLOGPOSTS_IMAGE_URL)
    public ResponseEntity<String> uploadImage(@RequestParam MultipartFile image,
                                              @PathVariable Long blogId) throws IOException {
        return ResponseEntity.status(HttpStatus.OK).body(imageService.uploadImage(image, blogId));
    }
}
