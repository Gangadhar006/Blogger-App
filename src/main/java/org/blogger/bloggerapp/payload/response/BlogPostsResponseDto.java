package org.blogger.bloggerapp.payload.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BlogPostsResponseDto {
    private Long id;
    private String title;
    private String content;
    private byte[] imageData;
    private LocalDateTime createdAt;
}