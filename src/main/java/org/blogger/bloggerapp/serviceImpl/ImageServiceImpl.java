package org.blogger.bloggerapp.serviceImpl;

import lombok.RequiredArgsConstructor;
import org.blogger.bloggerapp.entity.BlogPosts;
import org.blogger.bloggerapp.exception.BlogNotFoundException;
import org.blogger.bloggerapp.repository.IBlogPostsRepository;
import org.blogger.bloggerapp.service.IImageService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Arrays;

import static org.blogger.bloggerapp.constants.AppConstants.BLOG_NOT_FOUND_MESSAGE;

@Service
@RequiredArgsConstructor
public class ImageServiceImpl implements IImageService {
    private final IBlogPostsRepository blogRepo;

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

    @Override
    public byte[] fetchImage(Long blogId) {
        BlogPosts post = getBlog(blogId);
        return post.getImageData();
    }
}
