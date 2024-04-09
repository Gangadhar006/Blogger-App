package org.blogger.bloggerapp.service;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface IImageService {
    String uploadImage(MultipartFile image, Long blogId) throws IOException;

    byte[] fetchImage(Long blogId);
}
