package org.blogger.bloggerapp.payload.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.blogger.bloggerapp.entity.Users;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BlogPostsRequestDto {
    private String title;
    private String content;
    private byte[] imageData;

    @JsonIgnore
    private Users user;
}